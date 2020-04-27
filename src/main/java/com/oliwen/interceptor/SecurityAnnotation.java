package com.oliwen.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by IntelliJ IDEA.
 * @author: liht
 * @date: 2019/1/13 6:17 PM
 * @description:  权限注解 是否进行校验，默认为校验
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SecurityAnnotation {
    //是否进行权限校验
    boolean auth() default true;
    //是否进行登录校验
    boolean login() default true;
}