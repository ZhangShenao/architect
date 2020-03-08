package william.designpattern.prototype.shallow;


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
        ShallowCopyPrototype prototype = new ShallowCopyPrototype();
        prototype.setId("1");
        prototype.setName("浅拷贝原型");
        List<String> alias = new ArrayList<>();
        alias.add("Alias1");
        alias.add("Alias2");
        prototype.setAlias(alias);

        //通过浅克隆创建新对象
        ShallowCopyPrototype cloned = prototype.clone();

        //浅克隆时,修改拷贝对象的引用属性时,同时也会影响到原型对象
        cloned.getAlias().add("Alias3");
        System.err.println("Prototype: " + prototype);
        System.err.println("Cloned: " + cloned);
    }

}
