package william.designprinciple.openclose;

/**
 * @Author zhangshenao
 * @Date 2020-03-03
 * @Description 课程打折的包装器
 */
public class DiscountCourseDecorator implements Course {
    private Course course;

    private double discount;

    public DiscountCourseDecorator(Course course, double discount) {
        this.course = course;
        this.discount = discount;
    }

    @Override
    public long getId() {
        return course.getId();
    }

    @Override
    public String getName() {
        return course.getName();
    }

    @Override
    public double getPrice() {
        return course.getPrice() * discount;
    }
}
