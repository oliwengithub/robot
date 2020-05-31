package com.oliwen;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;

public class Hello implements Job {

    public void hello () {
        System.out.println("hello-----------"+ LocalDateTime.now());
    }

    @Override
    public void execute (JobExecutionContext jobExecutionContext) throws JobExecutionException {
       // System.out.println("hello-----------");
    }
}
