package william.designprinciple.depencencyinversion;

/**
 * @Author zhangshenao
 * @Date 2019-09-12
 * @Description 依赖倒置原则:始终面向接口编程
 */
public class TestDependencyInversion {
    public static void main(String[] args) {
        Student student = new Student("Tom");
        student.study(new JavaCourse());
    }
}
