package william.designpattern.decorator;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description 加鸡蛋的装饰器
 */
public class EggDecorator extends PancakeDecorator {
    public EggDecorator(Pancake pancake) {
        super(pancake);
    }

    @Override
    public String material() {
        return pancake.material()
                + " + "
                + "一个鸡蛋";
    }

    @Override
    public int price() {
        return pancake.price() + 1;
    }
}
