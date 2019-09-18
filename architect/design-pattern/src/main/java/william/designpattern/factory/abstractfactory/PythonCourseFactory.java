package william.designpattern.factory.abstractfactory;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description 具体工厂, 创建具体产品族的产品
 */
public class PythonCourseFactory implements CourseFactory {
    @Override
    public Video createVideo() {
        return new PythonVideo();
    }

    @Override
    public Note createNote() {
        return new PythonNote();
    }

    @Override
    public Source createSource() {
        return new PythonSource();
    }
}
