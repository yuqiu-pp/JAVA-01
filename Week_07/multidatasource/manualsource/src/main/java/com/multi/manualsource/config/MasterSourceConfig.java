package com.multi.manualsource.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
// TODO 指定那些包下的 mapper 使用本数据源，并指定使用哪个SqlSessionFactory
// TODO 此处的 sqlSessionFactoryRef 即本配置中的注入的 SqlSessionFactory
@MapperScan(basePackages = "com.multi.manualsource.mapper.master", sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterSourceConfig {
    // bean name
    @Autowired
    @Qualifier("master")
    private DataSource masterDataSource;

    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory primarySqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        // 主数据源
        factoryBean.setDataSource(masterDataSource);
        factoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/master/*.xml"));
        return factoryBean.getObject();
    }

    @Bean(name = "masterTransactionManager")
    @Primary
    public DataSourceTransactionManager primaryTransactionManager() {
        return new DataSourceTransactionManager(masterDataSource);
    }

    @Bean(name = "masterSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate primarySqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(primarySqlSessionFactory());
    }

}
