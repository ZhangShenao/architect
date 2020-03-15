package william.designpattern.adapter.classadapter;

/**
 * @Author zhangshenao
 * @Date 2020-03-14
 * @Description Adapter:适配器类,将Adaptee转换为满足客户端需求的Target的实现类
 */
public class Adapter extends Adaptee implements Target {
    @Override
    public void request() {
        System.err.println("Adapter: 类适配");

        //直接使用Adaptee的方法
        super.specificRequest();
    }
}
