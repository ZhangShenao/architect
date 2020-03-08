package william.designpattern.prototype.json;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangshenao
 * @Date 2020-03-08
 * @Description
 */
public class Client {
    public static void main(String[] args) {
        //创建一个原型对象
        JsonDeepCopyPrototype prototype = new JsonDeepCopyPrototype();
        prototype.setId("1");
        prototype.setName("深拷贝原型");
        List<String> alias = new ArrayList<>();
        alias.add("Alias1");
        alias.add("Alias2");
        prototype.setAlias(alias);

        //通过深拷贝创建新对象
        JsonDeepCopyPrototype cloned = prototype.deepCopy();

        //基于深拷贝的原型模式,新对象与原型对象完全隔离,互不影响
        cloned.getAlias().add("Alias3");
        System.err.println("Prototype: " + prototype);
        System.err.println("Cloned: " + cloned);
    }
}
