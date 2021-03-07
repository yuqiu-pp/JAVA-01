package com.dynamic.source.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

// TODO 排除 DataSourceAutoConfiguration 的自动配置，
// 否则 会出现The dependencies of some of the beans in the application context form a cycle的错误
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@Configuration
// @PropertySource("classpth:")
// TODO 使用 MapperScan 指定包，自动注入相应的 mapper 类
// TODO 直接使用 Spring Boot 自动配置的 SqlSessionFactory，不再像manualsource中那样配置两个
@MapperScan(basePackages = "com.dynamic.source.mapper")
public class DynamicDataSourceConfig {

    @Bean("master")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("slave01")
    @ConfigurationProperties(prefix = "spring.datasource.slave01")
    public DataSource slaveDataSource01() {
        return DataSourceBuilder.create().build();
    }

    @Bean("slave02")
    @ConfigurationProperties(prefix = "spring.datasource.slave02")
    public DataSource slaveDataSource02() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    // TODO 使用注解 Primary， 优先dynamicDataSource()获取数据源
    @Primary
    public DataSource dynamicDataSource() {
        Map<Object, Object> dataSourceMap = new HashMap<>(3);
        dataSourceMap.put(DataSourceType.MASTER.name(), masterDataSource());
        dataSourceMap.put(DataSourceType.SLAVE01.name(), slaveDataSource01());
        dataSourceMap.put(DataSourceType.SLAVE02.name(), slaveDataSource02());

        DynamicDataSource dynamicDataSource = DynamicDataSource.getInstance();
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource());
        // 配置额外数据源
        dynamicDataSource.setTargetDataSources(dataSourceMap);

        return dynamicDataSource;
    }
}
