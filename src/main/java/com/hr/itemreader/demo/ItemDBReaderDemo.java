package com.hr.itemreader.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

//数据库
@Configuration
@EnableBatchProcessing
public class ItemDBReaderDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private DataSource dataSource;
    @Autowired
    @Qualifier(value = "dbWriter")
    private ItemWriter<User> dbWriter;
    @Bean
    public Job itemReaderDBJob() {
        return jobBuilderFactory.get("itemReaderDBJob")
                .start(itemReaderDbStep()).build();
    }

    public Step itemReaderDbStep() {
        return (Step) stepBuilderFactory.get("itemReaderDbStep")
                .<User, User>chunk(2).reader(dbJobReader()).writer(new ItemWirterDb()).build();
    }

    @Bean
    @StepScope
    public JdbcPagingItemReader<User> dbJobReader() {
        JdbcPagingItemReader<User> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(dataSource);
        reader.setFetchSize(2);
        reader.setRowMapper(new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setUser_name(resultSet.getString(2));
                user.setAge(resultSet.getInt(3));
                user.setAddress(resultSet.getString(4));
                return user;
            }
        });
        MySqlPagingQueryProvider mySqlPagingQueryProvider = new MySqlPagingQueryProvider();
        mySqlPagingQueryProvider.setSelectClause("id,user_name,age,address");
        mySqlPagingQueryProvider.setFromClause("from user_info");
        //根据那个字段排序
        Map<String, Order> stringOrderMap = new HashMap<>(1);
        stringOrderMap.put("id", Order.ASCENDING);
        mySqlPagingQueryProvider.setSortKeys(stringOrderMap);
        reader.setQueryProvider(mySqlPagingQueryProvider);
        return reader;
    }
}
