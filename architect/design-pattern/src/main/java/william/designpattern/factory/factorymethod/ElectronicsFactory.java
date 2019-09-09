package william.designpattern.factory.factorymethod;

import william.designpattern.factory.Electronics;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/31 13:05
 * @Description:工厂方法接口
 * 优点:一旦增加新的产品类型,直接扩展对应的工厂类即可,无需修改原代码,满足开闭原则
 * 缺点:实现相对复杂,当产品类型过多时会导致类爆炸
 */
public interface ElectronicsFactory<T extends Electronics> {
    T createElectronics();
}
