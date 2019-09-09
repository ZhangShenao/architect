package william.designpattern.factory.factorymethod;

import william.designpattern.factory.Computer;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/31 13:14
 * @Description:工厂方法接口的实现类——电脑工厂
 */
public class ComputerFactory implements ElectronicsFactory<Computer>{
    @Override
    public Computer createElectronics() {
        return new Computer();
    }
}
