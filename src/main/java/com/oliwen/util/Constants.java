package com.oliwen.util;

/**
 * Created by IntelliJ IDEA.
 * @author: liht
 * @date: 2019/1/17 9:53 PM
 * @description:  常量类
 */
public class Constants {

    /**session缓存key*/
    public static final String SESSION_SYSTEM_CONF = "sessionSystemConf";
    public static final String SESSION_ADMIN_USER = "sessionAdminUser";

    /**系统用户的状态*/
    public static final int SYSTEM_USER_STATUS_NORMAL = 0;
    public static final int SYSTEM_USER_STATUS_FORBID = 1;
    public static final int SYSTEM_USER_STATUS_DELETE = 2;

    /**系统角色的状态*/
    public static final int SYSTEM_ROLE_STATUS_NORMAL = 0;
    public static final int SYSTEM_ROLE_STATUS_DELETE = 1;

    public static final int URL_TYPE_MENU = 0;
    public static final int URL_TYPE_BUTTON = 1;
    public static final int URL_TYPE_OPERATION = 2;


    public static final String CODE_SUCCESS = "操作成功";
    public static final String CODE_FAIL = "操作失败";
    public static final String CODE_NOT_USER = "用户不存在";



    public static final String LOGIN_SUCCESS = "登录成功";
    public static final String LOGIN_FAIL = "用户名密码错误";





    public static final String SYSTEM_CONFIG_CODE_SYSTEM = "sys_conf";
    public static final String SYSTEM_CONFIG_CODE_EMAIL = "email_conf";
}
