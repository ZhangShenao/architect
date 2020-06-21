//package william.jvm.classloader;
//
//import java.io.FileInputStream;
//import java.lang.reflect.Method;
//
///**
// * @Author zhangshenao
// * @Date 2020-06-14
// * @Description 自定义类加载器, 打破双亲委派机制
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
//        BreakParentDelegateClassLoader loader = new BreakParentDelegateClassLoader("/Users/zhangshenao/Desktop/test");
//        Class<?> clazz = loader.loadClass("william.jvm.classloader.User");
//
//        //由于打破了双亲委派机制,因此对于自定义的类,都使用BreakParentDelegateClassLoader进行加载
//        ClassLoader classLoader = clazz.getClassLoader();
//        System.err.println("ClassLoader: " + classLoader.getClass().getName());
//        Object user = clazz.newInstance();
//        Method method = clazz.getMethod("show", null);
//        method.setAccessible(true);
//        method.invoke(user, null);
//
//    }
//}
