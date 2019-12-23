package com.hr.writerdb;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class ItemReaderDBDemo {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    @Qualifier(value = "flatFileReader")
    private ItemReader<? extends Customer> flatFileReader;
    @Autowired
    @Qualifier(value = "itemWriterDb")
    private ItemWriter<? super Customer> itemWriterDb;

    @Bean
    public Job itemWriterDemoDBJob() {
        return jobBuilderFactory.get("itemWriterDemoDBJob")
                .start(itemWriterDemoDBStep()).build();
    }

    @Bean
    public Step itemWriterDemoDBStep() {

        return stepBuilderFactory.get("itemWriterDemoDBStep")
                .<Customer, Customer>chunk(2).reader(flatFileReader).writer(itemWriterDb).build();
    }


}
