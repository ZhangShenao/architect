package william.designpattern.factory.abstractfactory;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description 抽象工厂模式:提供一个创建一系列相关或相互依赖对象的接口,而无须指定他们具体的类。主要应用于产品族概念
 * 优点:满足产品族的需求
 * 缺点:扩展新的产品类型时,需要修改源代码,不符合开闭原则
 */
public class TestAbstractFactory {
    public static void main(String[] args) {
        CourseFactory factory = new JavaCourseFactory();
        Video video = factory.createVideo();
        video.record();
        Note note = factory.createNote();
        note.edit();
        Source source = factory.createSource();
        source.develop();
    }
}
