package william.designpattern.composite.awt;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangshenao
 * @Date 2021-01-13
 * @Description 组件容器抽象父类
 */
public abstract class AbstractComponentContainer implements Component {
    protected String name;

    private List<Component> components;

    public AbstractComponentContainer(String name) {
        this.name = name;
        this.components = new ArrayList<>();
    }

    @Override
    public boolean isContainer() {
        return true;
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    @Override
    public void print() {
        printSelf();
        components.forEach(Component::print);
    }

    protected String getName() {
        return name;
    }

    protected abstract void printSelf();
}
