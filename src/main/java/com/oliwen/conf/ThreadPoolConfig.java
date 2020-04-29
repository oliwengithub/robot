package com.oliwen.conf;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Configuration
public class ThreadPoolConfig {

    /**
     * 异步执行任务的线程
     * @return
     */
    @Bean(name = "asyncThreadPool",destroyMethod = "shutdown")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor thread = new ThreadPoolTaskExecutor();
        //核心线程数，默认为1
        thread.setCorePoolSize(5);
        //最大线程数，默认为Integer.MAX_VALUE
        thread.setMaxPoolSize(20);
        //队列最大长度，一般需要设置值>=notifyScheduledMainExecutor.maxNum；默认为Integer.MAX_VALUE
        thread.setQueueCapacity(500);
        //线程池维护线程所允许的空闲时间，默认为60s
        thread.setKeepAliveSeconds(30);
        //完成任务自动关闭 , 默认为false-
        thread.setWaitForTasksToCompleteOnShutdown(true);
        //核心线程超时退出，默认为false
        thread.setAllowCoreThreadTimeOut(true);
        return thread;
    }

   /* //周期任务调度线程池
    @Bean(name = "scheduledExecutorService")
    public ScheduledExecutorService scheduledExecutorService() {
        //设置固定线程数
        return new ScheduledThreadPoolExecutor(50,
                new BasicThreadFactory.Builder().namingPattern("syncdata-schedule-pool-%d").daemon(true).build());
    }*/
}
