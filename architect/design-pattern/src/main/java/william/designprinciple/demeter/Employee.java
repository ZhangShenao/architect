package william.designprinciple.demeter;

import java.util.ArrayList;
import java.util.List;

import william.designprinciple.openclose.Course;
import william.designprinciple.openclose.JavaCourse;

/**
 * @Author zhangshenao
 * @Date 2020-03-03
 * @Description
 */
public class Employee {
    public int checkCourseNum() {
        List<Course> courses = findAllCourse();
        return courses.size();
    }

    private List<Course> findAllCourse() {
        List<Course> courses = new ArrayList<>();
        courses.add(new JavaCourse(1, "Java高级架构师培训", 5000D));
        return courses;
    }
}
