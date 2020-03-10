package william.designpattern.facade;

/**
 * @Author zhangshenao
 * @Date 2020-03-10
 * @Description
 */
public class SubSystemA implements SubSystem {
    @Override
    public void work() {
        System.err.println("子系统A工作");
    }
}
