package william.designpattern.factory.simplefactory;

import william.designpattern.factory.Course;
import william.designpattern.factory.JavaCourse;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description 简单工厂模式
 * 优点:将创建产品的逻辑整合到一处,通过客户端传入的参数决定具体创建什么类型的产品
 * 缺点:一旦新增产品类型,就需要修改代码,不符合开闭原则
 * 适用场景:产品类型较少、创建流程简单
 */
public class TestSimpleFactory {
    public static void main(String[] args) {
        Course course = SimpleCourseFactory.create(JavaCourse.class);
        course.record();
    }
}
