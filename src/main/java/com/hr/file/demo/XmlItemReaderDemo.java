package com.hr.file.demo;

import com.sun.xml.internal.stream.buffer.XMLStreamBufferMark;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

//@Configuration
//@EnableBatchProcessing
public class XmlItemReaderDemo {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job xmlItemReaderDemoJob() {
        return jobBuilderFactory.get("xmlItemReaderDemoJob").start(xmlItemReaderDemoStep()).build();
    }

    private Step xmlItemReaderDemoStep() {
        return stepBuilderFactory.get("xmlItemReaderDemoStep").<Consumer, Consumer>chunk(1)
                .reader(xmlFileReader()).writer(null).build();
    }

    @Bean
    @StepScope
    public StaxEventItemReader<? extends Consumer> xmlFileReader() {
        StaxEventItemReader reader = new StaxEventItemReader();
        reader.setResource(new ClassPathResource("customer.xml"));
        reader.setFragmentRootElementName("customer");
        XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
        Map<String, Class> map = new HashMap<>();
        map.put("customer", Customer.class);
        xStreamMarshaller.setAliases(map);
        reader.setUnmarshaller(xStreamMarshaller);
        return reader;
    }
}
