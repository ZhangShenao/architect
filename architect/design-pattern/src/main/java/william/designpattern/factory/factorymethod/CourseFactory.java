package william.designpattern.factory.factorymethod;

import william.designpattern.factory.Course;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description 工厂接口
 */
public interface CourseFactory {
    Course createCourse();
}
