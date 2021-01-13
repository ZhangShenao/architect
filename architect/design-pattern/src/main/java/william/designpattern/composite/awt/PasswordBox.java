package william.designpattern.composite.awt;

/**
 * @Author zhangshenao
 * @Date 2021-01-13
 * @Description
 */
public class PasswordBox extends AbstractComponent {
    public PasswordBox(String name) {
        super(name);
    }

    @Override
    public void print() {
        System.err.println("Print PasswordBox(" + getName() + ")");
    }
}
