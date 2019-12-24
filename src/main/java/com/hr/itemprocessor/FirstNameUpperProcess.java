package com.hr.itemprocessor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component(value = "firstNameUpperProcess")
public class FirstNameUpperProcess implements ItemProcessor<Customer, Customer> {
    @Override
    public Customer process(Customer item) throws Exception {
        Customer customer = new Customer();
        customer.setId(item.getId());
        customer.setFirstName(item.getFirstName().toUpperCase());
        customer.setLastName(item.getLastName());
        customer.setBirthday(item.getBirthday());
        return customer;
    }
}
