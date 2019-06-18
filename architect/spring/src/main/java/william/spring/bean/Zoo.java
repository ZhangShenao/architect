package william.spring.bean;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/18 13:32
 * @Description:
 */
public class Zoo {
    private Cat cat;
    private Dog dog;
    private Monkey monkey;

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public Monkey getMonkey() {
        return monkey;
    }

    public void setMonkey(Monkey monkey) {
        this.monkey = monkey;
    }

    @Override
    public String toString() {
        return "Zoo{" +
                "cat=" + cat +
                ", dog=" + dog +
                ", monkey=" + monkey +
                '}';
    }
}
