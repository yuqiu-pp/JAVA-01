/**
 *  双重检查锁
 *  为什么要进行两次判空检查？ 只要一次是否可以？
 */
public class DoubleCheckLockSingle {
    /**
     *  volatile 作用
     *  1. 禁止指令重排
     *  2. 每次都从主内存读变量，而不是线程栈的缓存
     */
    private volatile static DoubleCheckLockSingle single = null;

    private DoubleCheckLockSingle() {
        if (single != null) {
            throw new RuntimeException();
        }
    }

    public static DoubleCheckLockSingle getInstance() {
        if (single == null) {
            synchronized (DoubleCheckLockSingle.class) {
                if (single == null) {
                    /**
                     *  new Object时，JVM要做3件事
                     *  1. 分配内存空间
                     *  2. 初始化对象
                     *  3. 将对象指向分配的内存空间
                     */
                    single = new DoubleCheckLockSingle();
                }
            }
        }
        return single;
    }
}
