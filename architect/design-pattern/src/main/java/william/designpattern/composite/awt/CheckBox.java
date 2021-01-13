package william.designpattern.composite.awt;

/**
 * @Author zhangshenao
 * @Date 2021-01-13
 * @Description
 */
public class CheckBox extends AbstractComponent {
    public CheckBox(String name) {
        super(name);
    }

    @Override
    public void print() {
        System.err.println("Print CheckBox(" + getName() + ")");
    }
}
