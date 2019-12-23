package com.hr.db_writer_file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.validation.BindException;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DbJdbcConfig {
    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcPagingItemReader<Customer> flatDbToFileReader() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        JdbcPagingItemReader<Customer> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(dataSource);
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(new String[]{"id", "firstName", "lastName", "birthday"});
        DefaultLineMapper<Customer> mapper = new DefaultLineMapper<>();
        mapper.setLineTokenizer(tokenizer);
        mapper.setFieldSetMapper(new FieldSetMapper<Customer>() {
            @Override
            public Customer mapFieldSet(FieldSet fieldSet) throws BindException {
                Customer customer = new Customer();
                customer.setId(fieldSet.readLong("id"));
                customer.setFirstName(fieldSet.readString("firstName"));
                customer.setLastName(fieldSet.readString("lastName"));
                customer.setBirthday(fieldSet.readString("birthday"));
                return customer;
            }
        });
        MySqlPagingQueryProvider pagingQueryProvider = new MySqlPagingQueryProvider();
        pagingQueryProvider.setSelectClause("id,firstName");
        pagingQueryProvider.setFromClause("from customer");
        Map<String, Order> sort = new HashMap<>(1);
        sort.put("id", Order.ASCENDING);
        pagingQueryProvider.setSortKeys(sort);
        reader.setQueryProvider(pagingQueryProvider);
        return reader;
    }

    @Bean
    public FlatFileItemWriter<Customer> itemWriterDbToFile() throws Exception {
        FlatFileItemWriter<Customer> writer = new FlatFileItemWriter<>();
        String path = "";
        writer.setResource(new FileSystemResource(path));
        writer.setLineAggregator(new LineAggregator<Customer>() {
            ObjectMapper mapper = new ObjectMapper();

            @Override
            public String aggregate(Customer item) {
                try {
                    String s = mapper.writeValueAsString(item);
                    return s;
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
        writer.afterPropertiesSet();
        return writer;
    }
}
