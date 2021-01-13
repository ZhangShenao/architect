package william.designpattern.composite.awt;

/**
 * @Author zhangshenao
 * @Date 2021-01-13
 * @Description 抽象组件
 */
public abstract class AbstractComponent implements Component {
    protected String name;

    public AbstractComponent(String name) {
        this.name = name;
    }

    @Override
    public boolean isContainer() {
        return false;
    }

    protected String getName() {
        return name;
    }
}
