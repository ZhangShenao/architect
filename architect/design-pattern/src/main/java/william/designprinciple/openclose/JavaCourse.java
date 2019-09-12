package william.designprinciple.openclose;

/**
 * @Author zhangshenao
 * @Date 2019-09-12
 * @Description
 */
public class JavaCourse implements Course {
    private long id;
    private String name;
    private double price;

    public JavaCourse(long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }


    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
