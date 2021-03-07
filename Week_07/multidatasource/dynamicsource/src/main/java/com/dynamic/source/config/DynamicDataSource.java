package com.dynamic.source.config;

import com.dynamic.source.context.DynamicDataSourceContextHolder;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource{
    public static DynamicDataSource getInstance() {
        return new DynamicDataSource();
    }

    @Override
    // TODO 数据源路由策略.  spring通过策略来关联数据源的使用
    // DynamicDataSource中的数据源在Config中设置了
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getContextKey();
    }
}
