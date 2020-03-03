package william.designprinciple.demeter;

/**
 * @Author zhangshenao
 * @Date 2020-03-03
 * @Description 迪米特法则:尽量降低类与类之间的耦合,减少类与类不必要的交互
 */
public class Client {
    public static void main(String[] args) {
        Employee employee = new Employee();
        Leader leader = new Leader(employee);
        System.err.println(leader.checkCourseNum());
    }
}
