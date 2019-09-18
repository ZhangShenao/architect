package william.designpattern.factory.abstractfactory;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description
 */
public class JavaNote implements Note {
    @Override
    public void edit() {
        System.err.println("编写Java课堂笔记");
    }
}
