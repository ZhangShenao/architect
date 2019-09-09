package william.designpattern.factory.factorymethod;

import william.designpattern.factory.Mobile;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/31 13:09
 * @Description:工厂方法接口的实现类——手机工厂
 */
public class MobileFactory implements ElectronicsFactory<Mobile>{
    @Override
    public Mobile createElectronics() {
        return new Mobile();
    }
}
