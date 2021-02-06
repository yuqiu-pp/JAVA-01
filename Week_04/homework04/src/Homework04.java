import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

public class Homework04 {

    public static void main(String[] args) {

        // 在这里创建一个线程或线程池，
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        // int result = sum(); //这是得到的返回值
        long start = System.currentTimeMillis();
        System.out.print("正确值： " + sum());
        System.out.println(" 使用时间：" + (System.currentTimeMillis()-start) + "ms");

        int result = 0;
        // 异步执行
        // 1 Future
        start = System.currentTimeMillis();
        result = getByFuture(threadPool);
        System.out.print("1 - Future                    异步计算结果为："+result);
        // 确保  拿到result 并输出
        System.out.println(" 使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 2  FutureTask
        start = System.currentTimeMillis();
        result = getByFutureTask(threadPool);
        System.out.print("2 - FutureTask                异步计算结果为："+result);
        System.out.println(" 使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 3. CompletableFuture.join()
        start = System.currentTimeMillis();
        result = getByCompletableFutureJoin();
        System.out.print("3 - CompletableFuture.join    异步计算结果为："+result);
        System.out.println(" 使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 4. CompletableFuture.get()
        start = System.currentTimeMillis();
        result = getByCompletableFutureGet();
        System.out.print("4 - CompletableFuture.get     异步计算结果为："+result);
        System.out.println(" 使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 5. Execute while
        start = System.currentTimeMillis();
        result = getByWhile(threadPool);
        System.out.print("5 - Execute while             异步计算结果为："+result);
        System.out.println(" 使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 6. SynchronousQueue
        start = System.currentTimeMillis();
        result = getBySyncQueue(threadPool);
        System.out.print("6 - SynchronousQueue          异步计算结果为："+result);
        System.out.println(" 使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 7. AtomicReference
        start = System.currentTimeMillis();
        result = getByAtomicReference(threadPool);
        System.out.print("7 - AtomicReference           异步计算结果为："+result);
        System.out.println(" 使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 8. wait notify
        start = System.currentTimeMillis();
        result = getByWaitAndNotify(threadPool);
        System.out.print("8 - WaitAndNotify             异步计算结果为："+result);
        System.out.println(" 使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 9. join
        start = System.currentTimeMillis();
        result = getByJoin();
        System.out.print("9 - Join                      异步计算结果为："+result);
        System.out.println(" 使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 10. ForkJoinPool
        start = System.currentTimeMillis();
        result = getByForkJoinPool();
        System.out.print("10 - ForkJoinPool             异步计算结果为："+result);
        System.out.println(" 使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 11. CountDownLatch
        start = System.currentTimeMillis();
        result = getByCountDownLatch(threadPool);
        System.out.print("11 - CountDownLatch           异步计算结果为："+result);
        System.out.println(" 使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 12. CyclicBarrier
        start = System.currentTimeMillis();
        result = getByCyclicBarrier(threadPool);
        System.out.print("12 - CyclicBarrier            异步计算结果为："+result);
        System.out.println(" 使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 13.Phaser
        start = System.currentTimeMillis();
        result = getByPhaser(threadPool);
        System.out.print("13 - Phaser                   异步计算结果为："+result);
        System.out.println(" 使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 14.Semaphore
        start = System.currentTimeMillis();
        result = getBySemaphore(threadPool);
        System.out.print("14 - Semaphore                异步计算结果为："+result);
        System.out.println(" 使用时间："+ (System.currentTimeMillis()-start) + " ms");


        // 然后退出main线程
        //关闭线程池
        threadPool.shutdown();

    }

    private static int getByFuture(ExecutorService service) {
        Future<Integer> future = service.submit(Homework04::sum);
        int result = 0;
        try {
            result = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static int getByFutureTask(ExecutorService service) {
        FutureTask<Integer> futureTask = new FutureTask<>(Homework04::sum);
        service.submit(futureTask);
        int result = 0;
        try {
            result = futureTask.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static int getByCompletableFutureJoin() {
        return CompletableFuture.supplyAsync(Homework04::sum).join();
    }

    private static int getByCompletableFutureGet() {
        int result = 0;
        try {
            result = CompletableFuture.supplyAsync(Homework04::sum).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static int getByWhile(ExecutorService service) {
        // 不会局部变量作用域覆盖了
        final int[] result = {0};
        service.submit(() -> result[0] = sum());
        while (result[0] == 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result[0];
    }

    private static int getBySyncQueue(ExecutorService service) {
        SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();
        // 不需要返回值，不用submit
        service.execute(() -> synchronousQueue.offer(sum()));
        int result = 0;
        try {
            result = synchronousQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static int getByAtomicReference(ExecutorService service) {
        AtomicReference<Integer> atomicReference = new AtomicReference<>();
        service.execute(() -> atomicReference.set(sum()));
        // false 表示实际值不等于期待值，也就是值发生了变化，再跳出循环
        while (atomicReference.compareAndSet(null, null)){
            ;
        }
        return atomicReference.get();
    }

    private static int getByWaitAndNotify(ExecutorService service) {
        Object object = new Object();
        int[] result = {0};
        service.execute(() -> {
            synchronized (object) {
                result[0] = sum();
                object.notifyAll();
            }
        });

        try {
            // 等线程计算完sum
            synchronized (object) {
                object.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result[0];
    }

    private static int getByJoin() {
        int[] result = {0};
        Thread thread = new Thread(() -> result[0] = sum());
        thread.start();

        try {
            // 等子线程计算完sum
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result[0];
    }

    private static int getByForkJoinPool() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask task = new RecursiveTask() {
            @Override
            protected Integer compute() {
                return sum();
            }
        };
        Future<Integer> future = forkJoinPool.submit(task);
        forkJoinPool.shutdown();
        int result = 0;
        try {
            result = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static int getByCountDownLatch(ExecutorService service) {
        final int result[] = {0};
        // 计数初始值为1
        CountDownLatch latch = new CountDownLatch(1);
        service.execute(() -> {
            result[0] = sum();
            // 计数减1
            latch.countDown();
        });

        try {
            // 等待计数为0
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result[0];
    }

    private static int getByCyclicBarrier(ExecutorService service) {
        final int[] result = {0};
        // 两个线程所以参数是 2
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        service.execute(() -> {
            result[0] = sum();
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        return result[0];
    }

    private static int getByPhaser(ExecutorService service) {
        int result[] = {0};
        Phaser phaser = new Phaser();
        // 子线程register
        phaser.register();
        service.execute(() -> {
            result[0] = sum();
            phaser.arriveAndAwaitAdvance();
        });
        // 主线程register
        phaser.register();
        phaser.arriveAndAwaitAdvance();
        // 所有线程都到达arriveAndAwaitAdvance后一起执行
        return result[0];
    }

    private static int getBySemaphore(ExecutorService service) {
        final int[] result = {0};
        // 没有信号量
        Semaphore semaphore = new Semaphore(0);
        service.execute(() -> {
            result[0] = sum();
            // 释放一次，信号量+1
            semaphore.release();
        });

        try {
            // 子线程执行完后，才有信号量
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result[0];
    }



    // public class WhileThread {
    //     private int result = 0;
    // }


    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
