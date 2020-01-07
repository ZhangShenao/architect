package william.jvm.classloader;

import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * @Author zhangshenao
 * @Date 2020-01-07
 * @Description 自定义类加载器
 */
public class MyClassLoader extends ClassLoader {
    private String classPath;

    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] bytes = loadByte(name);
            return defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }

    private byte[] loadByte(String name) throws Exception {
        name = name.replaceAll("\\.", "/");
        FileInputStream fis = new FileInputStream(classPath + "/" + name + ".class");
        int len = fis.available();
        byte[] data = new byte[len];
        fis.read(data);
        fis.close();
        return data;
    }

    public static void main(String[] args) throws Exception {
        //使用自定义类加载器,从指定路径下加载Class
        MyClassLoader loader = new MyClassLoader("/Users/zhangshenao/Desktop/test");
        Class<?> clazz = loader.loadClass("william.jvm.classloader.User");
        System.err.println("ClassLoader: " + clazz.getClassLoader().getClass().getName());
        Object user = clazz.newInstance();
        Method method = clazz.getMethod("show", null);
        method.setAccessible(true);
        method.invoke(user, null);
    }
}
