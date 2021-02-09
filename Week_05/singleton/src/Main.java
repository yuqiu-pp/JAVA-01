import java.lang.reflect.Constructor;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("singleton");
        // 反射测试
        // try {
        //     reflectionTest();
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        doubleCheckLockSingleTest();
        staticClassSingleTest();
        hungrySingleTest();
        lazySingleTest();
        enumSingleTest();
        ThreadSingleTest();

    }

    private static void doubleCheckLockSingleTest() {
        DoubleCheckLockSingle single01 = DoubleCheckLockSingle.getInstance();
        DoubleCheckLockSingle single02 = DoubleCheckLockSingle.getInstance();
        System.out.println(judgeTwoInstance("DoubleCheckLock", single01, single02));
    }

    private static void staticClassSingleTest() {
        StaticClassSingle single01 = StaticClassSingle.getInstance();
        StaticClassSingle single02 = StaticClassSingle.getInstance();
        System.out.println(judgeTwoInstance("StaticClassSingleTest", single01, single02));
    }

    private static void hungrySingleTest() {
        HungrySingle single01 = HungrySingle.getInstance();
        HungrySingle single02 = HungrySingle.getInstance();
        System.out.println(judgeTwoInstance("HungrySingle", single01, single02));
    }

    private static void lazySingleTest() {
        LazySingle single01 = LazySingle.getInstance();
        LazySingle single02 = LazySingle.getInstance();
        System.out.println(judgeTwoInstance("LazySingle", single01, single02));
    }

    private static void enumSingleTest() {
        EnumSingle.INSTANCE.doSomething();
    }

    private static void ThreadSingleTest() {
        // 线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Future<?> futureA = executorService.submit(() -> {
            ThreadSingle.getInstance().setValue("A " + Thread.currentThread().getName());
            return ThreadSingle.getInstance().getValue();
        });

        // 当线程池数量为1时，task取到的是futureA对应线程中设置的值
        // 大于1时，取到null， 使用threadlocal时要注意线程池数量
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            ThreadSingle.getInstance().setValue("B " + Thread.currentThread().getName());
            return (String) ThreadSingle.getInstance().getValue();
        });
        executorService.submit(futureTask);
        try {
            System.out.println(futureA.get());
            System.out.println(futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

    private static String judgeTwoInstance(String module, Object obj01, Object obj02) {
        if (obj01 == obj02) {
            return module + " - 是同一个实例";
        } else {
            return module + " - 不是同一个";
        }
    }


    private static void reflectionTest() throws Exception {
        DoubleCheckLockSingle single01 = DoubleCheckLockSingle.getInstance();

        Class<DoubleCheckLockSingle> clazz = (Class<DoubleCheckLockSingle>) Class.forName("DoubleCheckLockSingle");
        Constructor<DoubleCheckLockSingle> c = clazz.getDeclaredConstructor();
        c.setAccessible(true);
        DoubleCheckLockSingle single03 = c.newInstance();
        // System.out.println(judgeTwoInstance("reflection", single01, single03));
    }




}
