package com.hr.itemreader.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class ItemReaderDemo {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job itemReaderDemoJob() {
        return jobBuilderFactory.get("itemReaderDemoJob1111")
                .start(itemreaderstep()).build();
    }

    private Step itemreaderstep() {
        return stepBuilderFactory.get("itemreaderstep11")
             .  <String,String>chunk(2).reader(itemreaderRead())
                .writer(new ItemWriter<String>() {
                    @Override
                    public void write(List<? extends String> list) throws Exception {
                        list.forEach(x->{
                            System.out.println(x);
                        });
                    }
                }).build();
    }

    private ItemReader<? extends String> itemreaderRead() {
        List<String> list = Arrays.asList("1", "2", "3", "3");

        return  new Myreader(list);
    }

}
