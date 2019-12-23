package com.hr.file.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.BindException;

//@EnableBatchProcessing
//@Configuration
public class FileItemReader {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    @Qualifier(value = "flatFileWriter")
    private ItemWriter<Entry> flatFileWriter;

    @Bean
    public Job fileItemReaderJob() {
        return jobBuilderFactory.get("fileItemReaderJob").start(fileItemReaderStep()).build();
    }

    @Bean
    public Step fileItemReaderStep() {
        return stepBuilderFactory.get("fileItemReaderStep").<Entry, Entry>chunk(1000)
                .reader(flatFileReader()).writer(null).build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<? extends Entry> flatFileReader() {
        FlatFileItemReader<Entry> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("201808.txt"));
        // reader.setLinesToSkip(1);
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setNames(new String[]{
                "NET_ID",
                "STATION_ID",
                "NODE_TYPE",
                "CREATION_TIME",
                "SHIFT_NO",
                "CREATION_TIME_1",
                "LICENSE_NO",
                "VEHICLE_SORT",
                "VEHICLE_CLASS",
                "VEHICLE_CASE",
                "PASSCARD_ID",
                "PASSCARD_TYPE",
                "AXIAL_NUM",
                "TOLL_WEIGHT",
                "OPERATOR_ID",
                "LOGON_TIME"
        });
        DefaultLineMapper<Entry> mapper = new DefaultLineMapper<>();
        mapper.setFieldSetMapper(new FieldSetMapper<Entry>() {
            @Override
            public Entry mapFieldSet(FieldSet fieldSet) throws BindException {
                Entry entry = new Entry();
                entry.setNET_ID(fieldSet.readString("NET_ID"));
                entry.setSTATION_ID(fieldSet.readString("STATION_ID"));
                entry.setNODE_TYPE(fieldSet.readString("NODE_TYPE"));
                entry.setCREATION_TIME(fieldSet.readString("CREATION_TIME"));
                entry.setSHIFT_NO(fieldSet.readInt("SHIFT_NO"));
                entry.setCREATION_TIME_1(fieldSet.readString("CREATION_TIME_1"));
                return entry;
            }
        });
        mapper.afterPropertiesSet();
        reader.setLineMapper(mapper);
        return reader;
    }
}
