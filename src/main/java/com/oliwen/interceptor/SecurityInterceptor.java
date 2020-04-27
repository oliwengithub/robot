package com.oliwen.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.oliwen.base.BaseController;
import com.oliwen.entity.ResultBody;
import com.oliwen.pojo.SystemMenu;
import com.oliwen.pojo.SystemOperation;
import com.oliwen.pojo.SystemUser;
import com.oliwen.service.system.SystemMenuService;
import com.oliwen.service.system.SystemOperationService;
import com.oliwen.util.Constants;
import com.oliwen.util.DateUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * @author: liht
 * @date: 2019/1/21 3:17 PM
 * @description:  权限拦截器
 */
@Component
public class SecurityInterceptor extends BaseController implements HandlerInterceptor{

    private static final String PERMISSION_DENIED = "您没有此操作权限哦，请联系管理员~";

    private static final boolean PASS = true;
    private static final boolean INTERCEPT = false;


    @Resource(name = "systemMenuService")
    private SystemMenuService systemMenuService;

    @Resource(name = "systemOperationService")
    private SystemOperationService systemOperationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.getSession().removeAttribute("currMenuChildrenTags");
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 从方法处理器中获取出要调用的方法
            Method method = handlerMethod.getMethod();
            SecurityAnnotation securityAnnotation = method.getAnnotation(SecurityAnnotation.class);
            /**登录校验*/
            //如果请求设置了登录校验
            if (securityAnnotation == null || securityAnnotation.login()) {
                //如果用户没有登录，则转发到登录页面
                if(request.getServletPath().startsWith("/system") && request.getSession().getAttribute(Constants.SESSION_ADMIN_USER) == null){
                    response.sendRedirect(request.getContextPath() + "/system/login");
                    return INTERCEPT;
                }
            }
            /**权限校验*/
            //如果请求没有设置权限校验，这直接跳过
            if (securityAnnotation != null && !securityAnnotation.auth()) {
                return PASS;
            }else{
                //验证权限,如果有权限，通过
                if (this.hasPermission(request)) {
                    return PASS;
                }
                //如果没有权限，拦截
                if(method.getReturnType() == ModelAndView.class){
                    //返回没有权限的页面
                    response.sendError(HttpStatus.FORBIDDEN.value(), PERMISSION_DENIED);
                }else if(method.getReturnType() == ResultBody.class){
                    ResultBody body = new ResultBody();
                    body.error(PERMISSION_DENIED);
                    writeJson(response,body.toJsonString());
                }else{
                    writeJson(response,PERMISSION_DENIED);
                }
                return INTERCEPT;
            }


        }
        return PASS;
    }

    /**
     * 是否有权限
     * @param request
     * @return
     */
    private boolean hasPermission(HttpServletRequest request) {
        SystemUser sessionUser = (SystemUser)request.getSession().getAttribute(Constants.SESSION_ADMIN_USER);
        if(sessionUser != null){
            List<SystemMenu> menus = systemMenuService.queryUserMenus(sessionUser.getUserId());
            for(SystemMenu menu : menus){
                if(menu.getUrl() != null){
                    String []urls = menu.getUrl().split(",");
                    if(Arrays.asList(urls).contains(request.getRequestURI())){
                        //如果是菜单，获取此菜单下所有子按钮菜单，并放入session
                        if(menu.getType() == Constants.URL_TYPE_MENU){
                            List<SystemMenu> currMenuChildrens = systemMenuService.getMenuTree(menu.getId(),menus);
                            List<String> tags = new ArrayList<>(currMenuChildrens.size());
                            currMenuChildrens.forEach(currMenuChildren->{
                                if(currMenuChildren.getType() == Constants.URL_TYPE_BUTTON){
                                    tags.add(currMenuChildren.getTag());
                                }
                            });
                            request.getSession().setAttribute("currMenuChildrenTags",tags);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public void writeJson(HttpServletResponse response ,String content){
        PrintWriter out = null;
        try {
            //设定类容为json的格式
            response.setContentType("application/json;charset=UTF-8");
            out = response.getWriter();
            //写到客户端
            out.write(content);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(out != null){
                out.close();
            }
        }
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        if(handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 从方法处理器中获取出要调用的方法
            Method method = handlerMethod.getMethod();
            operationRecord(request, response, method);
        }
    }

    public void operationRecord(HttpServletRequest request, HttpServletResponse response,Method method){
        OperationMapping operationMapping = method.getAnnotation(OperationMapping.class);
        if(operationMapping != null){
            SystemOperation operation = new SystemOperation();
            operation.setName(operationMapping.description());
            operation.setUrl(request.getRequestURI());
            operation.setParams(JSONObject.toJSONString(getPageData()));
            operation.setIp(ip());
            operation.setUserAgent(userAgent());
            operation.setCreateTime(new Date());
            operation.setCreateIdate(DateUtil.getIdate());
            SystemUser sessionUser = (SystemUser)getSessionUser();
            if(sessionUser != null){
                operation.setAdminId(sessionUser.getUserId());
                operation.setAdminName(sessionUser.getUserName());
            }
            systemOperationService.insertSystemOperation(operation);
        }
    }
}
