package chapter1.singleton;

/**
 * @author zhangshenao
 * Created on 2019-08-21
 * 使用枚举类强化单例属性
 */
public enum Singleton {
    INSTANCE;

    public static Singleton getInstance() {
        return INSTANCE;
    }
}
