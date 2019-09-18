package william.designpattern.factory.factorymethod;

import william.designpattern.factory.Course;
import william.designpattern.factory.JavaCourse;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description 具体工厂
 */
public class JavaCourseFactory implements CourseFactory {
    @Override
    public Course createCourse() {
        return new JavaCourse();
    }
}
