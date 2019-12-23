package com.hr.db_writer_file;

import com.hr.writerdb.Customer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class ItemReaderDBToFileDemo {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier("flatDbToFileReader")
    private ItemReader<? extends Customer> flatDbToFileReader;
    @Autowired
    @Qualifier("itemWriterDbToFile")
    private ItemWriter<? super Customer> itemWriterDbToFile;

    @Bean
    public Job itemWriterDemoDBToFileJob() {
        return jobBuilderFactory.get("itemWriterDemoDBToFileJob")
                .start(itemWriterDemoDBToFileStep()).build();
    }

    @Bean
    public Step itemWriterDemoDBToFileStep() {

        return stepBuilderFactory.get("itemWriterDemoDBToFileStep")
                .<Customer, Customer>chunk(2).reader(flatDbToFileReader).writer(itemWriterDbToFile).build();
    }


}
