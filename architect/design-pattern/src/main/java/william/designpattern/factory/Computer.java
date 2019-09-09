package william.designpattern.factory;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/31 12:55
 * @Description:
 */
public class Computer implements Electronics{
    @Override
    public void work() {
        System.err.println("电脑上网");
    }
}
