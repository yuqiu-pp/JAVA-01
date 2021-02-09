public class StaticClassSingle {

    private StaticClassSingle(){}

    /**
     *  静态内部类
     *  StaticClassSingle类加载时，不会触发new，实现了延迟加载
     *  唯一性通过static变量保证，
     */
    private static class Holder {
        private static final StaticClassSingle SINGLE_TON = new StaticClassSingle();
    }

    public static StaticClassSingle getInstance() {
        return Holder.SINGLE_TON;
    }
}
