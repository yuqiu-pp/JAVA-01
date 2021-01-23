import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * 自定义ClassLoader，读取class文件中字节，进行解码，并加载类
 */
public class MyClassloader extends ClassLoader{

    public static void main(String[] args){
        System.out.println("start");
        try {
            // findClass需要全限定类名
            Class<?> clazz = new MyClassloader().findClass("/Volumes/work/gktime/JavaCourse/week01/src/" + "Hello.xlass");
            Method method = clazz.getMethod("hello");
            // clazz.getDeclaredMethod()   区别 ?
            method.invoke(clazz.newInstance());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能是实现了，但所有东西都写在了一起，没有达到实现：职责单一原则，即只有一个原因导致接口改变
     * 功能拆分：读文件 -> 解码 -> defineclass
     */

    @Override
    protected  Class<?> findClass(String path) throws ClassNotFoundException {
        try {
            // 解码与读文件放在一起，使findClass更简洁
            // byte[] classData = readClassFile(name);
            // decodeClass(classData);
            byte[] classData = readClassFileAndDecode(path);
            String className = getClassName(path);

            // findClass只需要两个数据，只保留获取这两个数据的接口调用，其它都封装起来
            return defineClass(className, classData, 0, classData.length);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getClassName(String path) {
        String[] strings = path.split("/");
        String className = strings[strings.length - 1].split("\\.")[0];
        return className;
    }

    private byte[] readClassFileAndDecode(String path) throws IOException {
        // 文件流读取的打开次序
        File file = new File(path);
        FileInputStream in = new FileInputStream(file);
        byte[] bytes = new byte[in.available()];
        in.read(bytes);
        decodeClass(bytes);
        return bytes;
    }

    // 引用不需要返回
    private void decodeClass(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte)(255 - (int )bytes[i]);
        }
        // return bytes;
    }


    //
    // @Override
    protected Class<?> findClass01(String name) throws ClassNotFoundException{
        // 打开文件
        try {
            FileInputStream in = new FileInputStream(new File(name));
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (255 - (int)bytes[i]);
            }

            String[] strings = name.split("/");
            String className = strings[strings.length - 1].split("\\.")[0];
            return defineClass(className, bytes, 0, bytes.length);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
