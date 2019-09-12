package william.designprinciple.openclose;

/**
 * @Author zhangshenao
 * @Date 2019-09-12
 * @Description 开闭原则:对扩展开放,对修改关闭
 */
public class TestOpenClose {
    public static void main(String[] args) {
        Course course = new JavaDiscountCourse(1L, "折扣Java课程", 5000D);
        System.err.println("Price: " + course.getPrice());
    }
}
