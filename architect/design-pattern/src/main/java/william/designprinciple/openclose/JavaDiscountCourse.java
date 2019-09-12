package william.designprinciple.openclose;

/**
 * @Author zhangshenao
 * @Date 2019-09-12
 * @Description
 * 应用开闭原则——扩展一个新类JavaDiscountCourse处理折扣的逻辑,而不是直接修改JavaCourse的方法
 */
public class JavaDiscountCourse extends JavaCourse {
    public JavaDiscountCourse(long id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public double getPrice() {
        //打折
        return super.getPrice() * 0.6D;
    }
}
