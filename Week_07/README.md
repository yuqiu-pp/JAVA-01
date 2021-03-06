作业说明 week07

## 第14课

###### 2、（必做）读写分离-动态切换数据源版本1.0
```


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
