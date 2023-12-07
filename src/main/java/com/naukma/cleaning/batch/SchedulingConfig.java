package com.naukma.cleaning.batch;

import com.naukma.cleaning.dao.ReportDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@EnableScheduling
public class SchedulingConfig {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private ReportDao dao;

    @Autowired
    private Job job;

    @Scheduled(cron = "0 1-59 * * * *")
    public void performStatistics() throws Exception {
        log.info("Job scheduler started!");
        jobLauncher.run(job, new JobParametersBuilder().addLong("id",System.nanoTime()).toJobParameters());
        log.info("Job scheduler stopped! As result report was created: " + dao.findAll());
    }
}
