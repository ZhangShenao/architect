package william.designpattern.factory.simplefactory;

import william.designpattern.factory.Electronics;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/31 13:02
 * @Description:
 */
public class TestSimpleFactory {
    public static void main(String[] args) {
        Electronics mobile = SimpleElectronicsFactory.createElectronics(SimpleElectronicsFactory.MOBILE_TYPE);
        mobile.work();
        Electronics computer = SimpleElectronicsFactory.createElectronics(SimpleElectronicsFactory.COMPUTER_TYPE);
        computer.work();
    }
}
