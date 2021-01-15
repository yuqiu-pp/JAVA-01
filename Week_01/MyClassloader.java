import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassloader extends ClassLoader{

    public static void main(String[] args){
        System.out.println("start");
        try {
            Class clazz = new MyClassloader().findClass("/Volumes/work/gktime/JavaCourse/week01/src/" + "Hello.xlass");
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

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException{
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
