import java.lang.reflect.Constructor;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("singleton");
        // 反射测试
        reflectionTest();

        hungrySingleTest();
        lazySingleTest();
        doubleCheckLockSingleTest();
        staticClassSingleTest();
        enumSingleTest();

    }

    private static void reflectionTest() throws Exception {
        DoubleCheckLockSingle single01 = DoubleCheckLockSingle.getInstance();

        Class<DoubleCheckLockSingle> clazz = (Class<DoubleCheckLockSingle>) Class.forName("DoubleCheckLockSingle");
        Constructor<DoubleCheckLockSingle> c = clazz.getDeclaredConstructor();
        c.setAccessible(true);
        DoubleCheckLockSingle single03 = c.newInstance();
        // System.out.println(judgeTwoInstance("reflection", single01, single03));
    }

    private static void enumSingleTest() {
        EnumSingle.INSTANCE.doSomething();
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

    private static void doubleCheckLockSingleTest() {
        DoubleCheckLockSingle single01 = DoubleCheckLockSingle.getInstance();
        DoubleCheckLockSingle single02 = DoubleCheckLockSingle.getInstance();
        System.out.println(judgeTwoInstance("DoubleCheckLock", single01, single02));
    }

    private static String judgeTwoInstance(String module, Object obj01, Object obj02) {
        if (obj01 == obj02) {
            return module + " - 是同一个实例";
        } else {
            return module + " - 不是同一个";
        }
    }


}
