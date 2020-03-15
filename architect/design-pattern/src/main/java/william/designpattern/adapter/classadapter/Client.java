package william.designpattern.adapter.classadapter;

/**
 * @Author zhangshenao
 * @Date 2020-03-14
 * @Description 类适配器:Adapter实现Target接口,且继承Adaptee
 */
public class Client {
    public static void main(String[] args) {
        Target target = new Adapter();
        target.request();
    }
}
