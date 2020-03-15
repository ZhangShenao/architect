package william.designpattern.adapter.interfaceadapter;

/**
 * @Author zhangshenao
 * @Date 2020-03-14
 * @Description 接口适配器:当一个接口中方法过多时,直接实现该接口会使得类过于复杂和臃肿,此时可以借助接口适配器
 * 由接口适配器对所有接口方法提供默认的实现,具体的实现类只需实现自己关心的方法即可
 */
public class Client {
    public static void main(String[] args) {
        Target target = new Adaptee();

        target.request1();
        target.request2();
        target.request3();
        target.request4();
    }
}
