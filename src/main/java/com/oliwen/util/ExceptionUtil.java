package com.oliwen.util;

import org.slf4j.Logger;

public class ExceptionUtil {

    public static void loggerError(Logger logger,String name,Exception e,Object ...param){
        StringBuffer info = new StringBuffer();
        info.append("【").append(name).append("】").append("发生错误，错误信息为: {}, 参数为: {} ");
        logger.error(info.toString(),toString(e),param);
    }

    public static String toString(Exception e) {
        return e.getMessage();
        /*try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return "\r\n" + sw.toString() + "\r\n";
        } catch (Exception e2) {
            return "bad getErrorInfoFromException";
        }*/
    }

}
