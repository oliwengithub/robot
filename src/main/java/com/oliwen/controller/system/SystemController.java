package com.oliwen.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.oliwen.base.BaseController;
import com.oliwen.entity.ResultBody;
import com.oliwen.pojo.SystemMenu;
import com.oliwen.pojo.SystemUser;
import com.oliwen.service.system.SystemConfigService;
import com.oliwen.service.system.SystemMenuService;
import com.oliwen.service.system.SystemUserService;
import com.oliwen.util.Constants;
import com.oliwen.interceptor.OperationMapping;
import com.oliwen.interceptor.SecurityAnnotation;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system")
public class SystemController extends BaseController {

    @Value("${file.url.upload}")
    private String fileUploadUrl;

    @Value("${file.url.preview}")
    private String filePreviewUrl;

    @Resource(name = "systemUserService")
    private SystemUserService systemUserService;

    @Resource(name = "systemConfigService")
    private SystemConfigService systemConfigService;

    @Resource(name = "systemMenuService")
    private SystemMenuService systemMenuService;

    @RequestMapping("/index")
    @SecurityAnnotation(auth = false)
    public ModelAndView index(){
        ModelAndView mv = getView("system/index");
        SystemUser sessionUser = (SystemUser)getSessionUser();
        if(sessionUser != null){
            List<SystemMenu> menus = systemMenuService.queryUserTreeMenus(sessionUser.getUserId(), Constants.URL_TYPE_MENU);
            mv.addObject("menus",menus);
        }
        return mv;
    }

    @RequestMapping("/stat")
    @SecurityAnnotation(auth = false)
    public ModelAndView stat() {
        return getView("/system/stat");
    }

    @RequestMapping({"","/","/login"})
    @SecurityAnnotation(auth = false,login = false)
    public ModelAndView login(){
        ModelAndView mv = getView("system/login");
        setSession("fileUploadUrl",fileUploadUrl);
        setSession("filePreviewUrl",filePreviewUrl);
        setSessionUser(null);
        if(getSession(Constants.SESSION_SYSTEM_CONF) == null){
            Map<String,Object> conf = systemConfigService.getConfigObjectByCode(Constants.SYSTEM_CONFIG_CODE_SYSTEM);
            setSession(Constants.SESSION_SYSTEM_CONF,conf);
        }
        return mv;
    }

    @RequestMapping("/login/code")
    @SecurityAnnotation(auth = false,login = false)
    public void loginCode(HttpServletRequest request, HttpServletResponse response){
        try {
            CaptchaUtil.out(100,40,4,request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OperationMapping(value = "/login/check",description = "登录")
    @SecurityAnnotation(auth = false,login = false)
    public ResultBody check(String userName, String password, String code, HttpServletRequest request){
        ResultBody body = new ResultBody();
        if(existEmpty(code,userName,password)){
            return body.error("参数不能为空");
        }
        if(!CaptchaUtil.ver(code,request)){
            CaptchaUtil.clear(request);
            return body.error("验证码错误");
        }

        SystemUser systemUser = systemUserService.login(userName, getPassword(userName,password));
        if(systemUser == null){
            return body.error(Constants.LOGIN_FAIL);
        }
        //登录成功，把用户信息放入session
        setSessionUser(systemUser);
        return body;
    }

    @OperationMapping(value = "/login/out", description = "退出")
    @SecurityAnnotation(auth = false,login = false)
    public void out() throws IOException {
        getResponse().sendRedirect(getRequest().getContextPath() + "/system/login");
    }

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @RequestMapping(value = "/v1/getAllUrl")
    @SecurityAnnotation(auth = false,login = false)
    public String getAllUrl() {
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = handlerMapping.getHandlerMethods();
        List<Map<String, String>> list = new ArrayList<>(map.size());
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            Map<String, String> map1 = new HashMap<>(4);
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();

            PatternsRequestCondition p = info.getPatternsCondition();
            for (String url : p.getPatterns()) {
                map1.put("url", url);
            }
            //类名
            map1.put("className", method.getMethod().getDeclaringClass().getName());
            //方法名
            map1.put("method", method.getMethod().getName());
            RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
            for (RequestMethod requestMethod : methodsCondition.getMethods()) {
                map1.put("type", requestMethod.toString());
            }
            OperationMapping operationMapping = method.getMethod().getAnnotation(OperationMapping.class);
            map1.put("description", (operationMapping == null ? "" : operationMapping.description()));
            //是否需要权限
            SecurityAnnotation securityAnnotation = method.getMethod().getAnnotation(SecurityAnnotation.class);
            map1.put("checkAuth", (securityAnnotation == null ? "true" : securityAnnotation.auth()+""));
            map1.put("checkLogin", (securityAnnotation == null ? "true" : securityAnnotation.login()+""));
            list.add(map1);
        }
        return JSONObject.toJSONString(list);
    }
}
