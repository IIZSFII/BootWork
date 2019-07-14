package com.lottery.util.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;

//@Configuration
public class QuartzConfig {
    @Bean
    public Scheduler scheduler()throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        JobDetail oneJob = JobBuilder.newJob(OneJob.class).withIdentity("job1", "group1").build();
        JobDetail secondJob = JobBuilder.newJob(SecondJob.class).withIdentity("job2", "group2").build();

        TriggerBuilder<Trigger> newTrigger = TriggerBuilder.newTrigger();

        newTrigger.withIdentity("trigger1", "group1");
        newTrigger.withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"));
        Trigger oneTrigger = newTrigger.build();
        scheduler.scheduleJob(oneJob, oneTrigger);

        newTrigger.withIdentity("trigger2", "group2");
        newTrigger.withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?"));
        Trigger secondTrigger = newTrigger.build();
        scheduler.scheduleJob(secondJob, secondTrigger);

        scheduler.start();
        return scheduler;
    }
}
