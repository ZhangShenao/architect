package william.designpattern.factory;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/31 12:55
 * @Description:
 */
public class Camera implements Electronics{
    @Override
    public void work() {
        System.err.println("照相机拍照");
    }
}
