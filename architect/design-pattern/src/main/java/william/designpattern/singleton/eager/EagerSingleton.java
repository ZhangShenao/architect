package william.designpattern.singleton.eager;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description 饥饿单例模式
 * 优点:线程安全,执行效率高
 * 缺点:未被使用时就创建实例,浪费内存
 */
public class EagerSingleton {
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    //私有化构造器
    private EagerSingleton() {
    }

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
}
