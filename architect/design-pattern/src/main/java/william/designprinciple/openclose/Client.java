package william.designprinciple.openclose;

/**
 * @Author zhangshenao
 * @Date 2019-09-12
 * @Description 开闭原则:对扩展开放,对修改关闭
 */
public class Client {
    public static void main(String[] args) {
        Course course = new JavaCourse(1, "Java高级架构师培训", 5000D);
        Course discountCourse = new DiscountCourseDecorator(course, 0.6D);  //使用包装器模式,符合开闭原则
        System.err.println("Price: " + discountCourse.getPrice());
    }
}
