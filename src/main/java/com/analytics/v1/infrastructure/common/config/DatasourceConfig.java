package com.analytics.v1.infrastructure.common.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author townsend.wu
 * @create 2022-05-13 01:39
 * @desc 多数据源配置
 */
@Order(-1)
@Configuration
public class DatasourceConfig {
    @Primary
    @Bean(name = "sysDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.sys")
    public DataSourceProperties sysDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "sysDataSource")
    public DataSource sysDataSource(@Qualifier("sysDataSourceProperties") DataSourceProperties sysDataSourceProperties) {
        return sysDataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "adbDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.adb")
    public DataSourceProperties testDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "adbDataSource")
    public DataSource adbDataSource(@Qualifier("adbDataSourceProperties") DataSourceProperties adbDataSourceProperties) {
        return adbDataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    @DependsOn("sysDataSource")
    public JdbcTemplate jdbcTemplate(@Qualifier("sysDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean("adbJdbcTemplate")
    @DependsOn("adbDataSource")
    public JdbcTemplate adbJdbcTemplate(@Qualifier("adbDataSource") DataSource adbDataSource) {
        return new JdbcTemplate(adbDataSource);
    }
}
