package com.oliwen.conf;

import com.oliwen.interceptor.SecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by IntelliJ IDEA.
 * @author: liht
 * @date: 2019/1/21 3:49 PM
 * @description: web配置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SecurityInterceptor securityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 权限拦截器
         */
        InterceptorRegistration authInterceptor = registry.addInterceptor(securityInterceptor);
        //排除静态资源
        authInterceptor.excludePathPatterns("/css/**","/images/**","/js/**","/plug/**","/upload/**");
        //排除部分请求，不进入拦截器,不登录也可以访问
        authInterceptor.excludePathPatterns("/error");
        //拦截请求
        authInterceptor.addPathPatterns("/**");
    }
}