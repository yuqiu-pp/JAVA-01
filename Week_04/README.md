学习笔记

###### 2、（必做）思考有多少种方式，在main函数启动一个新线程，运行一个方法，拿到这 个方法的返回值后，退出主线程？
```
/homework04/src/Homework04.java

1 - Future                    异步计算结果为：24157817 使用时间：145 ms
2 - FutureTask                异步计算结果为：24157817 使用时间：66 ms
3 - CompletableFuture.join    异步计算结果为：24157817 使用时间：72 ms
4 - CompletableFuture.get     异步计算结果为：24157817 使用时间：66 ms
5 - Execute while             异步计算结果为：24157817 使用时间：72 ms
6 - SynchronousQueue          异步计算结果为：24157817 使用时间：72 ms
7 - AtomicReference           异步计算结果为：24157817 使用时间：96 ms
8 - WaitAndNotify             异步计算结果为：24157817 使用时间：72 ms
9 - Join                      异步计算结果为：24157817 使用时间：68 ms
10 - ForkJoinPool             异步计算结果为：24157817 使用时间：69 ms
11 - CountDownLatch           异步计算结果为：24157817 使用时间：67 ms
12 - CyclicBarrier            异步计算结果为：24157817 使用时间：65 ms
13 - Phaser                   异步计算结果为：24157817 使用时间：64 ms
14 - Semaphore                异步计算结果为：24157817 使用时间：65 ms
```

###### 4、（必做）把多线程和并发相关知识梳理一遍，画一个脑图
```
multi_thread.xmind
```