package william.designpattern.adapter.classadapter;

/**
 * @Author zhangshenao
 * @Date 2020-03-14
 * @Description Adaptee:已经存在的可以满足客户端需求的类,但是由于接口不兼容,无法直接使用
 */
public class Adaptee {
    public void specificRequest() {
        System.err.println("Adaptee: specificRequest");
    }
}
