package william.designpattern.decorator;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description 加香肠的装饰器
 */
public class SausageDecorator extends PancakeDecorator {
    public SausageDecorator(Pancake pancake) {
        super(pancake);
    }

    @Override
    public String material() {
        return pancake.material() + " + " + "一个香肠";
    }

    @Override
    public int price() {
        return pancake.price() + 2;
    }
}
