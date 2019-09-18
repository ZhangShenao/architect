package william.designpattern.factory.abstractfactory;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description
 */
public class JavaVideo implements Video {
    @Override
    public void record() {
        System.err.println("录制Java视频");
    }
}
