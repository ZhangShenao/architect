package william.designprinciple.interfacesegregation;

/**
 * @Author zhangshenao
 * @Date 2020-03-03
 * @Description
 */
public class Bird implements Flyable,Eatable{
    @Override
    public void eat() {
        System.err.println("鸟会吃东西");
    }

    @Override
    public void fly() {
        System.err.println("鸟会飞");
    }
}
