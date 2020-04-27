package com.oliwen.thread;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author: olw
 * @date: 2019/12/28 0028 15:41
 * @description:  开启周期性任务调度
 */
public class ThreadExecutor {
   /* private Integer threadID;

    public ThreadExecutor () {}

    public ThreadExecutor (Integer threadID) {

    }*/
    //ScheduledExecutorService scheduledExecutorService = ThreadPoolUtils.getInstance().getThreadPool();
   public  static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

   /* ScheduledExecutorService executorService = (ScheduledExecutorService) Executors.newScheduledThreadPool(10).scheduleAtFixedRate(new Runnable(){
        public void run(){

        }
    }, 0, 1000, TimeUnit.MICROSECONDS);*/
    //scheduleAtFixedRate 固定周期时间  不考虑方法执行时间
    //scheduleWithFixedDelay 固定延迟时间 	保证任务执行的间隔
}