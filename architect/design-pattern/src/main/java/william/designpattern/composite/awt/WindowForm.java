package william.designpattern.composite.awt;

/**
 * @Author zhangshenao
 * @Date 2021-01-13
 * @Description
 */
public class WindowForm extends AbstractComponentContainer {
    public WindowForm(String name) {
        super(name);
    }

    @Override
    protected void printSelf() {
        System.err.println("Print WindowForm(" + getName() + ")");
    }
}
