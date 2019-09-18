package william.designpattern.singleton.innerclass;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description 基于内部类实现的延迟加载单例模式
 * 优点:可以实现延迟加载,且基于JVM的类加载机制保证了线程安全
 */
public class InnerSingleton {
    private InnerSingleton() {
        //防止反射攻击
        if (SingletonHolder.INSTANCE != null) {
            throw new UnsupportedOperationException();
        }
    }

    public static InnerSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static final class SingletonHolder {
        private static InnerSingleton INSTANCE = new InnerSingleton();
    }

    //防止序列化攻击
    private Object readResolve() {
        return getInstance();
    }
}
