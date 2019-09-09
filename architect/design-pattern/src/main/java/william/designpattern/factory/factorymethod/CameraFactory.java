package william.designpattern.factory.factorymethod;

import william.designpattern.factory.Camera;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/31 13:17
 * @Description:工厂方法接口的实现类——相机工厂
 */
public class CameraFactory implements ElectronicsFactory<Camera> {
    @Override
    public Camera createElectronics() {
        return new Camera();
    }
}
