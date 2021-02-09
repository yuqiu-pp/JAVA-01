/**
 *  饿汉式
 *  优点：线程安全
 *  缺点：不支持延迟加载。即类加载时就创建，会延长加载类的时间。如果不用的话会浪费内存空间；
 *       不能传递参数；
 */
public class HungrySingle {
    // 加 final 修饰
    private static final HungrySingle single = new HungrySingle();

    private  HungrySingle() {
        if (single != null) {
            throw new RuntimeException();
        }
    }

    public static HungrySingle getInstance() {
        return single;
    }
}
