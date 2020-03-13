package william.designpattern.decorator;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description 装饰器模式:在不改变原有对象的基础之上,将功能附加到对象上。
 * 装饰器模式提供了比继承更有弹性的替代方案,可以动态地扩展原有对象的功能
 * 装饰器模式属于结构型模式。
 * <p>
 * 适用场景:动态扩展一个类的功能
 */
public class Client {
    public static void main(String[] args) {
        /*Pancake pancake = new PurePancake();
        pancake = new EggDecorator(pancake);
        pancake = new EggDecorator(pancake);
        pancake = new SausageDecorator(pancake);
        System.err.println("原料: " + pancake.material() + " 价格: " + pancake.price());*/

        double result = (13000000 * 0.8D) / (24 * 60 * 60 * 0.2);
        System.err.println(result);
    }
}
