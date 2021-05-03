package com.fooddeliveryservice.app;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;


@Configuration
public class DatabaseConnection {
    @Bean
    public DataSource getDataSource(){
        return  DataSourceBuilder
                .create()
                .url("jdbc:mysql://localhost:3306/fooddeliveryservice")
                .username("faisal")
                .password("faisal12345")
                .build();
    }
}
