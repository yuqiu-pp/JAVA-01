import java.util.concurrent.ConcurrentHashMap;

public class ThreadSingle {

    private static final ConcurrentHashMap<Long, ThreadSingle> instances = new ConcurrentHashMap<>();

    private Object value = null;

    private ThreadSingle() {}

    // 因为是不同线程的，所以这里不需要 锁
    public static ThreadSingle getInstance() {
        Long threadId = Thread.currentThread().getId();
        // 每个线程 拥有一个single实例
        instances.putIfAbsent(threadId, new ThreadSingle());
        return instances.get(threadId);
    }

    public void setValue(Object obj) {
        this.value = obj;
    }

    public Object getValue() {
        return this.value;
    }
}
