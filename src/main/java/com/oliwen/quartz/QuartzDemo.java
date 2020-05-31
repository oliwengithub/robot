package com.oliwen.quartz;

import com.oliwen.quartz.test.ChickenJob;
import org.quartz.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@DisallowConcurrentExecution
public class QuartzDemo implements Job {
    @Override
    public void execute (JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        JobDataMap dataMap = jobDetail.getJobDataMap();
        /**
         * 获取任务中保存的方法名字，动态调用方法
         */
        String methodName = dataMap.getString("jobMethodName");
        try {
            QuartzDemo job = new QuartzDemo();
            Method method = job.getClass().getMethod(methodName);
            method.invoke(job);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void test() {
        System.out.println("测试执行---------------"+ LocalDateTime.now());
    }
}
