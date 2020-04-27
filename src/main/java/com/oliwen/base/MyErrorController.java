package com.oliwen.base;

import com.alibaba.fastjson.JSONObject;
import com.oliwen.entity.PageData;
import com.oliwen.util.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * @author: liht
 * @date: 2018/12/5 1:51 PM
 * @description: 错误处理类 （处理400、500等）
 */
@Controller
public class MyErrorController implements ErrorController {

    private Logger logger = LoggerFactory.getLogger(MyErrorController.class);

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(getErrorPath());
        Map<String, Object> map = getErrorInfo(request);

        map.put("parameters",new PageData(request));
        map.put("ip", IpUtil.getIp(request));

        modelAndView.addAllObjects(map);
        logger.error("请求发生错误, 错误信息：{}", JSONObject.toJSONString(map));
        return modelAndView;
    }

    @Override
    public String getErrorPath() {
        return "/error/error";
    }

    public Map<String,Object> getErrorInfo(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>(5);
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if(statusCode == null){
            map.put("status", 999);
            map.put("error","None");
        }else{
            map.put("status",statusCode);
            try {
                map.put("error", HttpStatus.valueOf(statusCode).getReasonPhrase());
            } catch (Exception var5) {
                map.put("error", "Http Status " + statusCode);
            }
        }
        String path = (String)request.getAttribute("javax.servlet.error.request_uri");;
        map.put("path", path == null ? request.getRequestURI() : path);

        String message = (String)request.getAttribute("javax.servlet.error.message");
        map.put("message", message == null ? "No message available" : message);

        Throwable exception = (Throwable)request.getAttribute("javax.servlet.error.exception");
        map.put("exception",exception == null ? "" : exception.getMessage());

        map.put("time", new Date());
        return map;
    }

}