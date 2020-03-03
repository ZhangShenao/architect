package william.designprinciple.interfacesegregation;

/**
 * @Author zhangshenao
 * @Date 2020-03-03
 * @Description
 */
public class Dog implements Eatable, Swimmable {
    @Override
    public void eat() {
        System.err.println("狗会吃饭");
    }

    @Override
    public void swim() {
        System.err.println("狗会游泳");
    }
}
