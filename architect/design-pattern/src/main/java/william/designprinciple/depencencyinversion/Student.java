package william.designprinciple.depencencyinversion;

/**
 * @Author zhangshenao
 * @Date 2019-09-12
 * @Description
 */
public class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public void study(Course course) {
        System.err.println(name + ": " + course.study());
    }
}
