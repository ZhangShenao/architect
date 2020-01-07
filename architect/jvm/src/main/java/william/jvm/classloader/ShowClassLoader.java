package william.jvm.classloader;

/**
 * @Author zhangshenao
 * @Date 2020-01-07
 * @Description 展示系统中的ClassLoader
 */
public class ShowClassLoader {
    public static void main(String[] args) {
        System.err.println("启动类加载器: " + String.class.getClassLoader());
        System.err.println(
                "扩展类加载器: " + com.sun.crypto.provider.DESKeyFactory.class.getClassLoader().getClass().getName());
        System.err.println("应用类加载器: " + ShowClassLoader.class.getClassLoader().getClass().getName());
        System.err.println("系统默认类加载器: " + ClassLoader.getSystemClassLoader().getClass().getName());
    }
}
