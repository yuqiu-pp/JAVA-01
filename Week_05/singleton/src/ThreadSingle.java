import java.util.concurrent.ConcurrentHashMap;

public class ThreadSingle {

    private static final ConcurrentHashMap<Long, ThreadSingle> instances =
            new ConcurrentHashMap<>();

    private ThreadSingle() {}

    // 因为是不同线程的，所以这里不需要 锁
    public static ThreadSingle getInstance() {
        Long threadId = Thread.currentThread().getId();
        // if (!instances.contains(threadId)) {
        //     instances.put(threadId, new ThreadSingle());
        // }
        instances.putIfAbsent(threadId, new ThreadSingle());
        return instances.get(threadId);
    }
}
