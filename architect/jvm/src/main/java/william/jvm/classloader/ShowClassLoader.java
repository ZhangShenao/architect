package william.jvm.classloader;

import java.net.URL;
import java.util.Arrays;

import sun.misc.Launcher;

/**
 * @Author zhangshenao
 * @Date 2020-01-07
 * @Description 展示系统中的ClassLoader
 */
public class ShowClassLoader {
    public static void main(String[] args) {
       /* System.err.println("启动类加载器: " + String.class.getClassLoader());
        System.err.println(
                "扩展类加载器: " + com.sun.crypto.provider.DESKeyFactory.class.getClassLoader().getClass().getName());
        System.err.println("应用类加载器: " + ShowClassLoader.class.getClassLoader().getClass().getName());
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        System.err.println("系统默认类加载器: " + appClassLoader.getClass().getName());*/

        //每个ClassLoader内部维护了一个parent属性,委托了父类加载器
        /*ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader extClassLoader = appClassLoader.getParent();
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.err.println("appClassLoader: " + appClassLoader);
        System.err.println("extClassLoader: " + extClassLoader);
        System.err.println("bootstrapClassLoader: " + bootstrapClassLoader);*/

        URL[] bootstrapUrls = Launcher.getBootstrapClassPath().getURLs();
        System.err.println("bootstrapUrls: " + Arrays.toString(bootstrapUrls));
        System.err.println();

        System.err.println("extUrls: " + System.getProperty("java.ext.dirs"));
        System.err.println();
        System.err.println("appUrls: " + System.getProperty("java.class.path"));
    }
}
