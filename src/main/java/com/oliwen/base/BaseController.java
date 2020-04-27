package com.oliwen.base;

import com.oliwen.entity.PageData;
import com.oliwen.util.Constants;
import com.oliwen.util.IpUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * @author: liht
 * @date: 2018/12/19 9:33 AM
 * @description:  请求基类
 */
public class BaseController {

    private HttpServletRequest request;

    /**
     * 得到ModelAndView
     */
    public ModelAndView getView(String page) {
        ModelAndView mv = new ModelAndView(page);
        mv.addAllObjects(getPageData());
        return mv;
    }

    public void setSession(String key,Object value){
        getRequest().getSession().setAttribute(key, value);
    }
    public Object getSession(String key){
        return getRequest().getSession().getAttribute(key);
    }

    public void setSessionUser(Object user){
        getRequest().getSession().setAttribute(Constants.SESSION_ADMIN_USER, user);
    }

    public Object getSessionUser(){
        return getRequest().getSession().getAttribute(Constants.SESSION_ADMIN_USER);
    }

    /**
     * 得到request对象,因为是单列模式，所以basecontroller只有一个，"request.getRequestURI() == null"可判断是否为同一个请求里面
     */
    public HttpServletRequest getRequest() {
        if(request == null || request.getRequestURI() == null){
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        }
        return request;
    }

    public HttpServletResponse getResponse() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        return response;
    }

    /**
     * 得到所有参数
     */
    public PageData getPageData() {
        return new PageData(this.getRequest());
    }

    public String ip(){
        return IpUtil.getIp(this.getRequest());
    }

    public String userAgent(){
        return this.getRequest().getHeader("User-Agent");
    }

    public ModelAndView sendError(String msg) {
        try {
            getResponse().sendError(HttpServletResponse.SC_NOT_FOUND, msg);
        }catch (Exception e){
        }
        return null;
    }

    public boolean existEmpty(Object ...objs){
        if(objs != null && objs.length > 0){
            for(Object obj: objs){
                if(obj == null || "".equals(obj)){
                    return true;
                }
            }
        }
        return false;
    }

    public String getPassword(String userName,String password){
        StringBuffer str = new StringBuffer(userName);
        str.append("-").append(password);
        return DigestUtils.sha1Hex(str.toString());
    }
}
