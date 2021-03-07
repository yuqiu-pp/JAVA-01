package com.dynamic.source.context;

import com.dynamic.source.config.DataSourceType;

public class DynamicDataSourceContextHolder {

    public static final ThreadLocal<String> DATA_SOURCE_HOLDER = new ThreadLocal<>();

    public static void setContextKey(String dataSourceKey) {
        DATA_SOURCE_HOLDER.set(dataSourceKey);
    }

    public static String getContextKey() {
        String key = DATA_SOURCE_HOLDER.get();
        return key == null ? DataSourceType.MASTER.name() : key;
    }

    public static void removeContextKey() {
        DATA_SOURCE_HOLDER.remove();
    }

}
