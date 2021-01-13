package william.designpattern.composite.awt;

/**
 * @Author zhangshenao
 * @Date 2021-01-13
 * @Description
 */
public class Button extends AbstractComponent {
    public Button(String name) {
        super(name);
    }

    @Override
    public void print() {
        System.err.println("Print Button(" + getName() + ")");
    }
}
