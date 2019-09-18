package william.designpattern.factory.abstractfactory;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description
 */
public class JavaSource implements Source {
    @Override
    public void develop() {
        System.err.println("开发Java源码");
    }
}
