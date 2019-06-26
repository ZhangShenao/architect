package william.concurrent.cas;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/25 13:49
 * @Description:
 */
@Getter
@Setter
public class Person {
    private Address address;

    public static Person valueOf(String add){
        Person p = new Person();
        Address addr = new Address();
        addr.setAddr(add);
        p.setAddress(addr);
        return p;
    }

    @Getter
    @Setter
    public static class Address{
        private String addr;
    }
}
