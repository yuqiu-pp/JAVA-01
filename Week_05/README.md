作业说明

## 第9课
###### 1、（选做）使 Java 里的动态代理，实现一个简单的 AOP。
```
dynamicproxyAOP
- JDK动态代理
  优点：原始类即便新增接口，代理类也不需要修改；
  缺点：只能实现接口的代理；
- CGLib动态代理
  优点：原理是创建原始类的子类，所以没有接口的限制
```

###### 2、（必做）写代码实现 Spring Bean 的装配，方式越多越好（XML、Annotation 都可以）
```
beanloader/MyBeanFactory/xml
- BeanFactoryByXml.java 解析xml，生成bean实例，放入容器




```



## 第10课
###### 1、（选做）总结一下，单例的各种写法，比较它们的优劣。
```
singleton
- 饿汉模式 ：启动时实例化对象，影响应用启动时间；
- 懒汉模式 ：使用时才实例化对象(延迟加载)，但每次都会获取锁，并发性能低；
- 双重检查锁模式 ：延迟加载，并发性能高；
- 枚举模式 ：可以防止反射；
- 静态内部类	：延迟加载，并发性能高。实现简单；
- 线程之间的单例，类似于ThreadLocal

```

###### 3、（必做）给前面课程提供的 Student/Klass/School 实现自动配置和 Starter。
```
starterdemo

```

###### 6、（必做）研究一下 JDBC 接口和数据库连接池，掌握它们的设计和用法：
```
1）使用 JDBC 原生接口，实现数据库的增删改查操作。
jdbcdemo/jdbcnative

2）使用事务，PrepareStatement 方式，批处理方式，改进上述操作。
3）配置 Hikari 连接池，改进上述操作
jdbcdemo/jdbcTransaction

```