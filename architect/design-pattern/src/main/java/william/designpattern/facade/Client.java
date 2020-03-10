package william.designpattern.facade;

/**
 * @Author zhangshenao
 * @Date 2020-03-10
 * @Description 门面模式, 又叫外观模式, 由一个门面对多个子系统进行封装和统一调度, 客户端只需要与门面打交道, 不需要关心内部各子系统的细节
 */
public class Client {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.aWork();
        facade.bWork();
        facade.cWork();
    }
}
