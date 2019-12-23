package com.hr.springbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.JobLoader;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.JobStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 嵌套的job
 */
@Configuration
@EnableBatchProcessing
public class NestedJob {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private Job childJobOne;
    @Autowired
    private Job childJobOne2;
    @Autowired
    private JobLauncher jobLauncher;

    @Bean
    public Job parentJob(JobRepository repository, PlatformTransactionManager platformTransactionManager) {
        return jobBuilderFactory.get("parentJob")
                .start(childJob1(repository,platformTransactionManager))
                .next(childJob2(repository,platformTransactionManager))
                .build();
    }

    //特殊的step
    private Step childJob2(JobRepository repository, PlatformTransactionManager platformTransactionManager) {
        return new JobStepBuilder(new StepBuilder("childJob2"))
                .job(childJobOne2).launcher(jobLauncher).repository(repository)
                .transactionManager(platformTransactionManager).build();
    }

    private Step childJob1(JobRepository repository, PlatformTransactionManager platformTransactionManager) {
        return new JobStepBuilder(new StepBuilder("childJob1"))
                .job(childJobOne).launcher(jobLauncher).repository(repository)
                .transactionManager(platformTransactionManager).build();
    }

}
