package william.designpattern.factory.abstractfactory;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description
 */
public class PythonVideo implements Video {
    @Override
    public void record() {
        System.err.println("录制Python视频");
    }
}
