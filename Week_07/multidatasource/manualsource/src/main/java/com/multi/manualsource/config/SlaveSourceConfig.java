package com.multi.manualsource.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.multi.manualsource.mapper.slave", sqlSessionFactoryRef = "slavedSqlSessionFactory")
public class SlaveSourceConfig {
    @Autowired
    @Qualifier("slave")
    private DataSource slaveDataSource;

    @Bean(name = "slavedSqlSessionFactory")
    public SqlSessionFactory secondSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(slaveDataSource);       // 使用次数据源
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/slave/*.xml"));
        return factoryBean.getObject();
    }

    @Bean(name = "slaveTransactionManager")
    public DataSourceTransactionManager secondaryTransactionManager() {
        return new DataSourceTransactionManager(slaveDataSource);
    }

    @Bean(name = "slaveSqlSessionTemplate")
    public SqlSessionTemplate secondarySqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(secondSqlSessionFactory());
    }

}
