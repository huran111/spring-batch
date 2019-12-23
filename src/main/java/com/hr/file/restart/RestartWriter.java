package com.hr.file.restart;

import com.hr.file.demo.Customer;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "restartWriter")
public class RestartWriter  implements ItemStreamWriter<Customer> {


    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {

    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {

    }

    @Override
    public void close() throws ItemStreamException {

    }

    @Override
    public void write(List<? extends Customer> items) throws Exception {
        for (Customer item : items) {
            System.out.println(item);
        }
    }
}
