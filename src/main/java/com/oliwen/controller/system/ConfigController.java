package com.oliwen.controller.system;

import com.oliwen.base.BaseController;
import com.oliwen.entity.ResultBody;
import com.oliwen.interceptor.OperationMapping;
import com.oliwen.service.system.SystemConfigService;
import com.oliwen.util.Constants;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/system/config")
public class ConfigController extends BaseController {

    @Resource(name = "systemConfigService")
    private SystemConfigService systemConfigService;

    @RequestMapping("/website")
    public ModelAndView website(){
        ModelAndView mv = getView("system/config/website");
        Map<String,Object> conf = systemConfigService.getConfigObjectByCode(Constants.SYSTEM_CONFIG_CODE_SYSTEM);
        mv.addAllObjects(conf);
        return mv;
    }

    @OperationMapping(value = "/website/update", description = "更新网站设置")
    public ResultBody websiteUpdate(String value){
        ResultBody body = new ResultBody();
        boolean status = systemConfigService.updateConfigByCode(Constants.SYSTEM_CONFIG_CODE_SYSTEM,value);
        if(!status){
            return body.error(Constants.CODE_FAIL);
        }
        //重新更新系统配置信息
        Map<String,Object> conf = systemConfigService.getConfigObjectByCode(Constants.SYSTEM_CONFIG_CODE_SYSTEM);
        setSession(Constants.SESSION_SYSTEM_CONF,conf);
        return body;
    }

    @RequestMapping("/email")
    public ModelAndView email(){
        ModelAndView mv = getView("system/config/email");
        Map<String,Object> conf = systemConfigService.getConfigObjectByCode(Constants.SYSTEM_CONFIG_CODE_EMAIL);
        mv.addAllObjects(conf);
        return mv;
    }

    @OperationMapping(value = "/email/update",description = "更新邮箱配置")
    public ResultBody emailUpdate(String value){
        ResultBody body = new ResultBody();
        boolean status = systemConfigService.updateConfigByCode(Constants.SYSTEM_CONFIG_CODE_EMAIL,value);
        if(!status){
            return body.error(Constants.CODE_FAIL);
        }
        return body;
    }
}
