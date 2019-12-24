package com.hr.itemprocessor;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class ItemProcessorDemo {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    @Qualifier("dbJdbcReader")
    private ItemReader<Customer> dbJdbcReader;
    @Autowired
    @Qualifier("dbFileWriter")
    private ItemWriter<Customer> dbFileWriter;
    @Autowired
    @Qualifier("firstNameUpperProcess")
    private ItemProcessor<? super Customer, ? extends Customer> firstNameUpperProcess;
    @Autowired
    @Qualifier("idFilterProcessor")
    private ItemProcessor<? super Customer, ? extends Customer> idFilterProcessor;

    @Bean
    public Job itemProcessorDemoJob() {
        return jobBuilderFactory.get("itemProcessorDemoJob")
                .start(itemProcessorDemoStep()).build();
    }

    @Bean
    public Step itemProcessorDemoStep() {
        return stepBuilderFactory.get("itemProcessorDemoStep")
                .<Customer, Customer>chunk(10).reader(dbJdbcReader).processor(processor()).writer(dbFileWriter).build();
    }

    /**
     * 多种处理方式
     *
     * @return
     */
    @Bean
    public CompositeItemProcessor<Customer, Customer> processor() {
        CompositeItemProcessor<Customer, Customer> processor = new CompositeItemProcessor<>();
        List<ItemProcessor<? super Customer, ? extends Customer>> list = new ArrayList<>();
        list.add(firstNameUpperProcess);
        list.add(idFilterProcessor);
        processor.setDelegates(list);
        return processor;
    }
}
