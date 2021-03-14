作业说明 week08

##  第15课

######  2.（必做）设计对前面的订单表数据进行水平分库分表，拆分 2 个库，每个库 16 张表。
```
springspherejdbc

order_info 分库分表

运行情况：
10:24:46.573 [nio-8080-exec-4] ShardingSphere-SQL       : Actual SQL: ds1 ::: insert into order_info15 (goods_id, id)

10:24:47.340 [nio-8080-exec-5] ShardingSphere-SQL       : Actual SQL: ds0 ::: insert into order_info11 (goods_id, id)

10:24:48.140 [nio-8080-exec-6] ShardingSphere-SQL       : Actual SQL: ds1 ::: insert into order_info8 (goods_id, id)

10:24:49.187 [nio-8080-exec-7] ShardingSphere-SQL       : Actual SQL: ds0 ::: insert into order_info4 (goods_id, id)

10:24:49.951 [nio-8080-exec-8] ShardingSphere-SQL       : Actual SQL: ds1 ::: insert into order_info2 (goods_id, id)

10:24:50.706 [nio-8080-exec-9] ShardingSphere-SQL       : Actual SQL: ds0 ::: insert into order_info4 (goods_id, id)


遇到的问题：
1.分库分表都使用雪花算法产品的order_info的id时，结果只有两种ds0.order_info0、ds1.order_info1。
原因：



```

