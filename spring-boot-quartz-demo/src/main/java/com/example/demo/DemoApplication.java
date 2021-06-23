package com.example.demo;

import java.util.UUID;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired private Scheduler scheduler;
    @Autowired private PersonRepo repo;

    @Override
    public void run(String... args) throws Exception {
      JobDetail jobDetail = buildJobDetail();
      Trigger trigger = buildJobTrigger(jobDetail);
      scheduler.scheduleJob(jobDetail, trigger);
    }

  private JobDetail buildJobDetail() {
    JobDataMap jobDataMap = new JobDataMap();

    jobDataMap.put("message", "Msg-"+ UUID.randomUUID().toString());

    return JobBuilder.newJob(SampleJob.class)
        .withIdentity(UUID.randomUUID().toString(), "sample-jobs")
        .withDescription("Send SampleMsg Job")
        .requestRecovery(true)
        .usingJobData(jobDataMap)
        .storeDurably()
        .build();
  }

  private Trigger buildJobTrigger(JobDetail jobDetail) {
    return TriggerBuilder.newTrigger()
        .forJob(jobDetail)
        .withIdentity(jobDetail.getKey().getName(), "sample-triggers")
        .withDescription("SampleJob Trigger")
        //.startAt(Date.from(Instant.now()))
        //.withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
        .startNow()
        .withSchedule(CronScheduleBuilder.cronSchedule("*/30 * * * * ?"))
        .build();
  }
}
