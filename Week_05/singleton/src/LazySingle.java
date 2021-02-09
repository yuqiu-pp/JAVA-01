public class LazySingle {

    private static LazySingle single = null;

    private LazySingle() {
        if (single != null) {
            throw new RuntimeException();
        }
    }

    /**
     *  缺点：每次都要获取锁，并发性能太低
     */
    public static synchronized LazySingle getInstance() {
        if (single == null) {
            single = new LazySingle();
        }
        return single;
    }
}
