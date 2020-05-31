package com.oliwen;

import com.oliwen.entity.Page;
import com.oliwen.pojo.QuartzGroup;
import com.oliwen.pojo.SystemMenu;
import com.oliwen.service.quartz.QuartzGroupService;
import com.oliwen.service.quartz.QuartzService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;


public class TestQuartzAPI  {

    public static void main (String[] args) throws SchedulerException {
        test01();
    }

   public static void test01() throws SchedulerException {

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        JobDetail build = newJob(Hello.class).withIdentity("myJob", "hello").build();

        Trigger trigger = newTrigger().withIdentity("myJob", "hello").startNow().withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(2).repeatForever()).build();

        scheduler.scheduleJob(build, trigger);
        scheduler.start();
    }
}
