package com.hr.springbatch.config;

import com.hr.springbatch.listener.MyChunkListener;
import com.hr.springbatch.listener.MyJobListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ListenerDemo {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job listenerJob() {
        return jobBuilderFactory.get("listenerJob").start(step1111()).listener(new MyJobListener())
                .build();
    }

    @Bean
    public Step step1111() {
        return stepBuilderFactory.get("step1111").<String,String>chunk(5)
                .faultTolerant().listener(new MyChunkListener())
                .reader(read()).writer(write()).build();
    }

    private ItemWriter<String> write() {
        return new ItemWriter<String>() {
            @Override
            public void write(List<? extends String> list) throws Exception {
                for (String s : list) {
                    System.out.println(s);
                }
            }
        };
    }

    private ItemReader<String> read() {
        return new ListItemReader<>(Arrays.asList("23","23","232"));
    }
}
