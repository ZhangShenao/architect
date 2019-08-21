package chapter1;

/**
 * @Author zhangshenao \
 * @Date 2019-08-21
 * @Description 工具类——通过私有构造器禁止实例化
 */
public class UtilityClass {
    //工具方法
    public static void utililtMethod() {
        //TODO
    }

    private UtilityClass() {
        //通过提供一个私有的无参构造器,禁止编译器自动生成默认无参构造器,从而禁止对工具类的实例化
        throw new AssertionError();
    }
}
