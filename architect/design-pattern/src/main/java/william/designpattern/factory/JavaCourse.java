package william.designpattern.factory;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description
 */
public class JavaCourse implements Course {
    @Override
    public void record() {
        System.err.println("录制Java课程");
    }
}
