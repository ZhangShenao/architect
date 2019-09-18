package william.designpattern.factory.abstractfactory;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description
 */
public class PythonNote implements Note {
    @Override
    public void edit() {
        System.err.println("编写Python课堂笔记");
    }
}
