package william.concurrent.atomic;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/20 16:26
 * @Description:
 */
public class User {
    private String name;
    /*public*/ volatile int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
