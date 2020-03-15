package william.designpattern.adapter.objectadapter;

/**
 * @Author zhangshenao
 * @Date 2020-03-14
 * @Description 对象适配器,内部维护一个Adaptee实例
 */
public class Adapter implements Target{
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }


    @Override
    public void request() {
        System.err.println("Adapter: 进行对象适配");
        adaptee.specificRequest();
    }
}
