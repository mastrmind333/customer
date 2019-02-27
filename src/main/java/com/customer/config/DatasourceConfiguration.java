package com.customer.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.customer"})
public class DatasourceConfiguration {

    @Value("${spring.datasource.maximum-pool-size:3}")
    private int maxPoolSize;

    @Value("${spring.datasource.connection-timeout:30000}")
    private int connectionTimeout;

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Bean
    @Primary
    public DataSource primaryDataSource() {
        DataSource ds = dataSourceProperties.initializeDataSourceBuilder().build();

        return ds;
    }
}
