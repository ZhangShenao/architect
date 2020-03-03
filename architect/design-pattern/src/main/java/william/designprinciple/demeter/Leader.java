package william.designprinciple.demeter;

/**
 * @Author zhangshenao
 * @Date 2020-03-03
 * @Description
 */
public class Leader {
    private Employee employee;

    public Leader(Employee employee) {
        this.employee = employee;
    }

    public int checkCourseNum(){
        //查看Course的相关信息时,Leader只需与Employee交互即可,不需要了解Course的细节
        return employee.checkCourseNum();
    }
}
