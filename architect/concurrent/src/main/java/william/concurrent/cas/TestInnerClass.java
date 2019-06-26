package william.concurrent.cas;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/25 13:55
 * @Description:
 */
public class TestInnerClass {
    public static void main(String[] args) {
        Person p = Person.valueOf("BeiJing");
        Person.Address address = p.getAddress();
        System.err.println(address.getClass().getCanonicalName());
        System.err.println(address.getClass().getSuperclass());
    }
}
