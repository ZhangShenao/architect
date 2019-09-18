package william.designpattern.singleton.enumsingleton;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description 基于枚举实现的单例模式, 基于JDK底层的保证, 天然避免反射和序列化的攻击
 */
public enum EnumSingleton {
    INSTANCE;

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }
}
