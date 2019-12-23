package com.hr.file.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.io.Resource;

import java.util.function.Consumer;

//@Configuration
//@EnableBatchProcessing
public class MultiFileItemReaderDemo {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Value("classpath:/file*.txt")
    private Resource[] resources;

    @Bean
    public Job multiFileItemReaderDemoJob() {
        return jobBuilderFactory.get("multiFileItemReaderDemoJob")
                .start(multiFileItemReaderDemoStepJob()).build();
    }

    @Bean
    @StepScope
    public Step multiFileItemReaderDemoStepJob() {
        stepBuilderFactory.get("multiFileItemReaderDemoStepJob").<Customer, Customer>chunk(1)
                .reader(multiFIleReader()).writer(null).build();
        return null;
    }

    /**
     * 多文件读取
     *
     * @return
     */
    @Bean
    @StepScope
    public MultiResourceItemReader<? extends Customer> multiFIleReader() {
        MultiResourceItemReader<Customer> resourceItemReader = new MultiResourceItemReader<>();
        resourceItemReader.setDelegate(null);
        resourceItemReader.setResources(resources);
        return resourceItemReader;
    }
}
