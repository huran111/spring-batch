package com.hr.itemreader.demo;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.Iterator;
import java.util.List;

public class Myreader implements ItemReader<String> {
    private Iterator<String> iterator;

    public Myreader(List<String> list) {
        this.iterator = list.iterator();
    }

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
       if(iterator.hasNext()){
           System.out.println(Thread.currentThread().getName());
           return this.iterator.next();
       }else {
           return null;
       }

    }
}
