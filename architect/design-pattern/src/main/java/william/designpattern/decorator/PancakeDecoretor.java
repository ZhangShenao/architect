package william.designpattern.decorator;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description
 */
public abstract class PancakeDecoretor implements Pancake {
    protected Pancake pancake;

    public PancakeDecoretor(Pancake pancake) {
        this.pancake = pancake;
    }
}
