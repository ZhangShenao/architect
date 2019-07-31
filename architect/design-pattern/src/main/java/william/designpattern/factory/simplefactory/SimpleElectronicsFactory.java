package william.designpattern.factory.simplefactory;

import william.designpattern.factory.Camera;
import william.designpattern.factory.Computer;
import william.designpattern.factory.Electronics;
import william.designpattern.factory.Mobile;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/31 12:56
 * @Description:简单工厂
 * 优点:实现简单,易于理解,适用于产品类型较少的情况
 * 缺点:每次增加新的产品类型,都需要修改简单工厂的代码,不满足开闭原则
 */
public class SimpleElectronicsFactory {
    public static String MOBILE_TYPE = "mobile";
    public static String COMPUTER_TYPE = "computer";
    public static String CAMERA_TYPE = "camera";

    public static Electronics createElectronics(String type) {
        if (MOBILE_TYPE.equalsIgnoreCase(type)) {
            return new Mobile();
        }
        if (COMPUTER_TYPE.equalsIgnoreCase(type)) {
            return new Computer();
        }
        if (CAMERA_TYPE.equalsIgnoreCase(type)) {
            return new Camera();
        }

        //每次增加新的产品类型,都需要修改简单工厂的代码,不满足开闭原则
        throw new IllegalArgumentException("Illegal type: " + type);
    }
}
