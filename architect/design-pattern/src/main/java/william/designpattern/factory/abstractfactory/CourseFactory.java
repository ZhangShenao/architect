package william.designpattern.factory.abstractfactory;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description 抽象工厂接口, 定义了创建产品族的方法
 */
public interface CourseFactory {
    Video createVideo();

    Note createNote();

    Source createSource();
}
