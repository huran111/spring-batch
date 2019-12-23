package com.hr.file.restart;

import com.hr.file.demo.Customer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class RestartDemo {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier("restartWriter")
    private ItemWriter restartWriter;

    @Autowired
    @Qualifier("restartReader")
    private ItemReader<Customer> restartReader;

    @Bean
    public Job restartsJob() {
        return jobBuilderFactory.get("restartsJob").start(restartStep()).build();
    }

    @Bean

    public Step restartStep() {
        return stepBuilderFactory.get("restartStep").<Customer, Customer>chunk(10).reader(restartReader)
                .writer(restartWriter).build();
    }
}
