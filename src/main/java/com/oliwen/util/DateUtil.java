package com.oliwen.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Date getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(sdf.format(new Date()));
        } catch (Exception e) {
        }
        return null;
    }

    public static Date toDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(date);
        } catch (Exception e) {
        }
        return null;
    }

    public static int getIdate() {
        return getIdate(new Date());
    }

    public static int getIdate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return Integer.parseInt(sdf.format(date));
    }

    /**
     * 获取当前时间之后的num分钟 加分钟
     * @param num
     * @return
     */
    public static Date afterMinuteTime(int num){
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.MINUTE, num);
        return beforeTime.getTime();
    }

    /**
     * 获取当前时间之前的num分钟 减分钟
     * @param num
     * @return
     */
    public static Date beforMinuteTime(int num){
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.MINUTE, -num);
        return beforeTime.getTime();
    }

    /**
     * 返回两个时间相差的秒
     * @param startDate
     * @param endDate
     * @return
     */
    public static int differSecond(Date startDate,Date endDate) {
        int second = (int)((endDate.getTime() - startDate.getTime()) / 1000);
        return second;
    }
}
