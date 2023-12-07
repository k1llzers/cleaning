package com.naukma.cleaning.batch;

import com.naukma.cleaning.dao.entities.OrderEntity;
import com.naukma.cleaning.dao.entities.ReportEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
//@EnableBatchProcessing//(dataSourceRef = "batchDataSource", transactionManagerRef = "batchTransactionManager")
@RequiredArgsConstructor
public class BatchConfig {

    @Bean
    public Job getMonthStats(JobRepository repository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("statisticsJob",repository)
                .incrementer(new RunIdIncrementer())
                .start(getStep(repository, transactionManager))
                .build();
    }

    @Bean
    public Step getStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step", jobRepository)
                .<List<OrderEntity>, ReportEntity> chunk(1, transactionManager)
//                .<List<OrderEntity>, List<OrderEntity>> chunk(1, transactionManager)
                .reader(getReader())
                .processor(getProcessor())
                .writer(getWriter())
                .build();
    }

    @Bean
    @StepScope
    public OrderReader getReader() {
//        return new OrderReader2();
        return new OrderReader();
    }

    @Bean
    @StepScope
    public StatisticsProcessor getProcessor() {
        return new StatisticsProcessor();
    }

    @Bean
    @StepScope
    public StatisticsWriter getWriter() {
//        return new StatisticsWriter2();
        return new StatisticsWriter();
    }
}