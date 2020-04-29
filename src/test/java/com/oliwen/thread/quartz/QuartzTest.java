package com.oliwen.thread.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTest {
    public static void main (String[] args) {
        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // and start it off
            scheduler.start();
            System.out.println("初始化配置");
            scheduler.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}
