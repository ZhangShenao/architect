package william.designpattern.state;

/**
 * @Author zhangshenao
 * @Date 2020-03-19
 * @Description 状态模式:将对象的行为与其内部的状态进行绑定,当状态转换时,行为随之发生改变,使得对象看起来好像是修改了它所属的类
 */
public class Client {
    public static void main(String[] args) {
        AppContext context = new AppContext();
        context.collect();
        context.comment("老铁双击666");
    }
}
