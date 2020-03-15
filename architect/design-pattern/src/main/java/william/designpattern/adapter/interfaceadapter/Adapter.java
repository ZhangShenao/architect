package william.designpattern.adapter.interfaceadapter;

/**
 * @Author zhangshenao
 * @Date 2020-03-14
 * @Description 接口适配器, 对接口中的方法提供默认实现, 具体的实现类直接继承适配器即可
 */
public class Adapter implements Target {
    @Override
    public void request1() {
        System.err.println("request1 默认实现");
    }

    @Override
    public void request2() {
        System.err.println("request2 默认实现");
    }

    @Override
    public void request3() {
        System.err.println("request3 默认实现");
    }

    @Override
    public void request4() {
        System.err.println("request4 默认实现");
    }
}
