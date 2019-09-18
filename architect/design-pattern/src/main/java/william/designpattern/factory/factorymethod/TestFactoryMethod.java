package william.designpattern.factory.factorymethod;

import william.designpattern.factory.Course;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description 工厂方法模式
 * 定义一个创建产品的工厂接口,让实现这个工厂接口的具体工厂来决定具体实例化哪个产品。工厂方法让类的实例化推迟到子类中进行。
 * 优点:用户只需要关心所需产品对应的工厂,无须关心创建细节。而且可以方便地扩展产品类型,符合开闭原则
 */
public class TestFactoryMethod {
    public static void main(String[] args) {
        CourseFactory factory = new JavaCourseFactory();
        Course course = factory.createCourse();
        course.record();
    }
}
