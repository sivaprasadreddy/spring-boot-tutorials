package com.example.demo;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SampleJob extends QuartzJobBean {
    private final PersonRepo repo;

    @SneakyThrows
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {

        log.info("Started at {}", LocalDateTime.now());
        Person person = repo.findById(1L).orElse(new Person());
        log.info("Age={}", person.getAge());
        person.setAge(person.getAge() + 1);
        log.info("sleeping for 15 seconds");
        Thread.sleep(15 * 1000);
        log.info("Woke up");
        repo.save(person);
        log.info("Completed at {}", LocalDateTime.now());
    }
}
