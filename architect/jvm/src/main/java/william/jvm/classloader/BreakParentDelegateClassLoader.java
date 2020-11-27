//package william.jvm.classloader;
//
//import java.io.FileInputStream;
//import java.lang.reflect.Method;
//
///**
// * @Author zhangshenao
// * @Date 2020-06-14
// * @Description 自定义类加载器, 重写loadClass()方法, 打破双亲委派机制
// */
//public class BreakParentDelegateClassLoader extends ClassLoader {
//    private String classPath;
//
//    public BreakParentDelegateClassLoader(String classPath) {
//        this.classPath = classPath;
//    }
//
//    //重写loadClass方法,打破双亲委派机制
//    @Override
//    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
//        synchronized (getClassLoadingLock(name)) {
//            //1. 检查该类是否已经被加载过。如果已加载过，则直接返回，保证类的唯一性
//            Class<?> c = findLoadedClass(name);
//            long t0 = System.nanoTime();
//
//            if (c == null) {
//                //2. 如果是自定义的类,则采用BreakParentDelegateClassLoader自定义类加载器进行加载
//                if (isCustomizedClass(name)) {
//                    c = findClass(name);
//                } else {
//                    //3. 非自定义类,仍然保持默认的双亲委派机制进行加载
//                    c = super.loadClass(name, resolve);
//                }
//                long t1 = System.nanoTime();
//
//                sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
//                sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
//                sun.misc.PerfCounter.getFindClasses().increment();
//            }
//            if (resolve) {
//                resolveClass(c);
//            }
//            return c;
//        }
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
//    private boolean isCustomizedClass(String className) {
//        return className != null && className.startsWith("william.jvm");
//    }
//
//    public static void main(String[] args) throws Exception {
//        //使用自定义类加载器,从指定路径下加载Class
//        BreakParentDelegateClassLoader loader1 = new BreakParentDelegateClassLoader("/Users/zhangshenao/Desktop/app1");
//        Class<?> clazz1 = loader1.loadClass("william.jvm.classloader.User");
//
//        //因为打破了双亲委派机制,因此即使加载了全限定类名相同的类,也可以做到同时记在互不隔离
//        BreakParentDelegateClassLoader loader2 = new BreakParentDelegateClassLoader("/Users/zhangshenao/Desktop/app2");
//        Class<?> clazz2 = loader2.loadClass("william.jvm.classloader.User");
//
//        //由于打破了双亲委派机制,因此对于自定义的类,都使用BreakParentDelegateClassLoader进行加载
//        ClassLoader classLoader = clazz1.getClassLoader();
//        System.err.println("ClassLoader: " + classLoader.getClass().getName());
//
//        //自定义类加载器的parent是AppClassLoader
//        System.err.println("Parent ClassLoader: " + classLoader.getParent().getClass().getName());
//
//        Object user1 = clazz1.newInstance();
//        Method method1 = clazz1.getMethod("show", null);
//        method1.setAccessible(true);
//        method1.invoke(user1, null);
//
//        Object user2 = clazz2.newInstance();
//        Method method2 = clazz2.getMethod("show", null);
//        method2.setAccessible(true);
//        method2.invoke(user2, null);
//
//        //判断两个类相等的条件: 全限定类名相同 && 由同一个类加载器加载
//        System.err.println("Class Equals: " + (clazz1.equals(clazz2)));
//
//    }
//}
