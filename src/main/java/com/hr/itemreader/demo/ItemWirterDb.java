package com.hr.itemreader.demo;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "dbWriter")
public class ItemWirterDb implements ItemWriter<User> {

    @Override
    public void write(List<? extends User> list) throws Exception {
        list.forEach(x -> {
            System.out.println(x);
        });
    }
}
