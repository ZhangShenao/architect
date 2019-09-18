package william.designpattern.factory.factorymethod;

import william.designpattern.factory.Course;
import william.designpattern.factory.PythonCourse;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description
 */
public class PythonCourseFactory implements CourseFactory {
    @Override
    public Course createCourse() {
        return new PythonCourse();
    }
}
