package william.designpattern.factory;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description
 */
public class PythonCourse implements Course {
    @Override
    public void record() {
        System.err.println("录制Python课程");
    }
}
