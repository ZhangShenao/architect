package william.designpattern.prototype.simple;

/**
 * @Author zhangshenao
 * @Date 2020-03-08
 * @Description 原型模式:以系统中已经存在的一个对象为原型，通过复制这个原型对象的属性,创建一个新的对象,可以省略对象创建和初始化造成的大量开销,提升创建对象的效率
 */
public class Client {
    public static void main(String[] args) {
        //首先创建一个原型对象
        ConcretePrototype prototype = new ConcretePrototype();
        prototype.setId("1");
        prototype.setName("原型实例");
        System.err.println("Prototype: " + prototype);


        //通过复制已有的原型实例的属性,创建一个新的对象
        Prototype cloned = prototype.clone();
        System.err.println("Cloned: " + cloned);

        System.err.println("Equals: " + (prototype == cloned));
    }
}
