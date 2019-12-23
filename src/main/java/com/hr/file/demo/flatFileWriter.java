package com.hr.file.demo;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;
@Component(value = "flatFileWriter")
public class flatFileWriter implements ItemWriter<Entry> {
    @Override
    public void write(List<? extends Entry> list) throws Exception {
        for (Entry entry : list) {
            System.out.println(entry.toString());
        }
    }
}
