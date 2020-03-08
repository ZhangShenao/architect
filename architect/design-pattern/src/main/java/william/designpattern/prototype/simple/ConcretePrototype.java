package william.designpattern.prototype.simple;

import lombok.Data;
import lombok.ToString;

/**
 * @Author zhangshenao
 * @Date 2020-03-08
 * @Description 原型对象实现类
 */
@Data
@ToString
public class ConcretePrototype implements Prototype {
    private String id;

    private String name;

    @Override
    public Prototype clone() {
        ConcretePrototype p = new ConcretePrototype();
        p.id = this.id;
        p.name = this.name;
        return p;
    }
}
