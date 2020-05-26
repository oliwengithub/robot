package com.oliwen.conf.quartz;

import org.quartz.Scheduler;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author: olw
 * @date: 2020/4/29 0029 16:24
 * @description:  quartz 配置类
 */
@Configuration
public class SchedulerConfig {


	@Resource
	private SpringJobFactory springJobFactory;

    @Bean(name="SchedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setAutoStartup(true);
        /*启动延迟5s*/
        factory.setStartupDelay(5);
        factory.setJobFactory(springJobFactory);
        return factory;
    }

    /**
     * @author: olw
     * @Date: 2020/4/29 0029 16:23
    */
    @Bean
    public QuartzInitializerListener executorListener() {
       return new QuartzInitializerListener();
    }

    /**
     * @author: olw
     * @Date: 2020/4/29 0029 16:23
     */
    @Bean(name="Scheduler")
    public Scheduler scheduler() throws IOException {
        return schedulerFactoryBean().getScheduler();
    }

}