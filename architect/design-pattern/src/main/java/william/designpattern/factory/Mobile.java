package william.designpattern.factory;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/31 12:49
 * @Description:
 */
public class Mobile implements Electronics{
    @Override
    public void work() {
        System.err.println("手机拨打电话");
    }
}
