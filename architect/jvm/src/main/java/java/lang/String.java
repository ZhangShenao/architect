package java.lang;

/**
 * @Author zhangshenao
 * @Date 2020-01-07
 * @Description 尝试使用自定义的java.lang.String类,由于JVM Classloader的双亲委派机制,该自定义类无法被加载,避免了安全性问题
 */
public class String {
    public static void main(String[] args) {
        System.err.println("Show String");
    }
}
