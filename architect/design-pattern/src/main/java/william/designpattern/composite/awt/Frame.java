package william.designpattern.composite.awt;

/**
 * @Author zhangshenao
 * @Date 2021-01-13
 * @Description
 */
public class Frame extends AbstractComponentContainer {
    public Frame(String name) {
        super(name);
    }

    @Override
    protected void printSelf() {
        System.err.println("Print Frame(" + getName() + ")");
    }
}
