package william.designpattern.factory.factorymethod;

import william.designpattern.factory.Computer;
import william.designpattern.factory.Mobile;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/31 13:18
 * @Description:
 */
public class TestFactoryMethod {
    public static void main(String[] args) {
        ElectronicsFactory<Mobile> mobileFactory = new MobileFactory();
        Mobile mobile = mobileFactory.createElectronics();
        mobile.work();

        ElectronicsFactory<Computer> computerFactory = new ComputerFactory();
        Computer computer = computerFactory.createElectronics();
        computer.work();
    }
}
