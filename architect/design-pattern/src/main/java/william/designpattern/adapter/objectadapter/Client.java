package william.designpattern.adapter.objectadapter;

/**
 * @Author zhangshenao
 * @Date 2020-03-14
 * @Description 对象适配器:Adapter实现Target接口,同时内部维护一个Adaptee实例,通过Adaptee实现目标功能
 */
public class Client {
    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        Target target = new Adapter(adaptee);
        target.request();
    }
}
