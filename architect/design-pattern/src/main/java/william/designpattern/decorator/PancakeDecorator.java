package william.designpattern.decorator;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description
 */
public abstract class PancakeDecorator implements Pancake {
    protected Pancake pancake;

    public PancakeDecorator(Pancake pancake) {
        this.pancake = pancake;
    }
}
