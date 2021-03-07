作业说明 week07

## 第14课

###### 2、（必做）读写分离-动态切换数据源版本1.0
```
multidatasource / manualsource
1) 配置两个数据源，master支持写，slave只读
├─config ----------- // 数据源配置
├─service -------    // 注入两个数据源对应的Mapper
├─entity ----------- // 实体类
│ ├─master
│ └─slave
├─mapper ----------- 
│ ├─master
│ └─slave

|─resources ----------- 
│ ├─mapper  
│   ├─master
│   └─slave

2）基于操作 AbstractRoutingDataSource 和自定义注解 readOnly
multidatasource / dynamicsource

├─annotation ---- // 自定义注解 @ReadOnly
├─aop ----------- // 切换slave数据源
├─config -------- // 数据源配置。 路由策略：线程上下文获取设置的数据源
├─context ------- // ThreadLocal保存数据源上下文
├─entity -------- 
├─mapper --------  
├─service -------  


```

###### 3、（必做）读写分离-数据库框架版本2.0
```
shardingspherejdbc / springspherejdbc

yaml文件设置数据源
spring:
  shardingsphere:
    datasource:
      names: ds-master, ds-slave-1, ds-slave-2

    masterslave:
      name: ms
      master-data-source-name: ds-master
      slave-data-source-names: ds-slave-1, ds-slave-2
      load-balance-algorithm-type: round_robin

运行效果
[nio-8080-exec-1] ShardingSphere-SQL       : Rule Type: master-slave
[nio-8080-exec-1] ShardingSphere-SQL       : SQL: select * from user; ::: DataSources: ds-slave-1
[nio-8080-exec-2] ShardingSphere-SQL       : Rule Type: master-slave
[nio-8080-exec-2] ShardingSphere-SQL       : SQL: select * from user; ::: DataSources: ds-slave-2
[nio-8080-exec-3] ShardingSphere-SQL       : Rule Type: master-slave
[nio-8080-exec-3] ShardingSphere-SQL       : SQL: insert into user (name, age)
        values ( ?,
        ?) ::: DataSources: ds-master
[nio-8080-exec-4] ShardingSphere-SQL       : Rule Type: master-slave
[nio-8080-exec-4] ShardingSphere-SQL       : SQL: select * from user; ::: DataSources: ds-slave-1
```
