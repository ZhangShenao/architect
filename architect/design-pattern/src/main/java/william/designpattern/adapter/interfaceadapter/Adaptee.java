package william.designpattern.adapter.interfaceadapter;

/**
 * @Author zhangshenao
 * @Date 2020-03-15
 * @Description
 */
public class Adaptee extends Adapter {
    @Override
    public void request3() {
        System.err.println("Adaptee: request3");
    }

    @Override
    public void request4() {
        System.err.println("Adaptee: request4");
    }
}
