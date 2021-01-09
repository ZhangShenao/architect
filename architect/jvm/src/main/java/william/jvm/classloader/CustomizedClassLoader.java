//package william.jvm.classloader;
//
//import java.io.FileInputStream;
//import java.lang.reflect.Method;
//
///**
// * @Author zhangshenao
// * @Date 2020-01-07
// * @Description 自定义类加载器, 实现findClass()方法
// * 仍然保持双亲委派机制
// */
//public class CustomizedClassLoader extends ClassLoader {
//    private String classPath;
//
//    public CustomizedClassLoader(String classPath) {
//        this.classPath = classPath;
//    }
//
//    @Override
//    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        try {
//            byte[] bytes = loadByte(name);
//            return defineClass(name, bytes, 0, bytes.length);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new ClassNotFoundException();
//        }
//    }
//
//    private byte[] loadByte(String name) throws Exception {
//        name = name.replaceAll("\\.", "/");
//        FileInputStream fis = new FileInputStream(classPath + "/" + name + ".class");
//        int len = fis.available();
//        byte[] data = new byte[len];
//        fis.read(data);
//        fis.close();
//        return data;
//    }
//
//    public static void main(String[] args) throws Exception {
//        //使用自定义类加载器,从指定路径下加载Class
//        CustomizedClassLoader loader = new CustomizedClassLoader("/Users/zhangshenao/Desktop/test");
//        Class<?> clazz = loader.loadClass("william.jvm.classloader.User");
//
//        //由于类加载的双亲委派机制,在classpath下可以找到william.jvm.classloader.User,所以会使用AppClassLoader进行加载
//        //如果在classpath下找不到william.jvm.classloader.User,则会使用自定义类加载器CustomClassLoader进行加载
//        ClassLoader classLoader = clazz.getClassLoader();
//        System.err.println("Current ClassLoader: " + classLoader.getClass().getName());
//
//        //自定义类加载器的parent是AppClassLoader
//        System.err.println("Parent ClassLoader: " + classLoader.getParent().getClass().getName());
//        Object user = clazz.newInstance();
//        Method method = clazz.getMethod("show", null);
//        method.setAccessible(true);
//        method.invoke(user, null);
//
//    }
//}
