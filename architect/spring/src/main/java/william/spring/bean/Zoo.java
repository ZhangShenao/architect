package william.spring.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/18 13:32
 * @Description:
 */
@Getter
@Setter
public class Zoo {
    private Cat cat;
    private Dog dog;
    private Monkey monkey;

    public Zoo(Cat cat, Dog dog) {
        this.cat = cat;
        this.dog = dog;
        System.err.println("Zoo(Cat cat, Dog dog)");
    }

    public Zoo(Dog dog, Monkey monkey) {
        this.dog = dog;
        this.monkey = monkey;
        System.err.println("Zoo(Dog dog, Monkey monkey)");
    }

    /*public Zoo(Cat cat, Dog dog, Monkey monkey) {
        this.cat = cat;
        this.dog = dog;
        this.monkey = monkey;
        System.err.println("Zoo(Cat cat, Dog dog, Monkey monkey)");
    }
*/

    @Override
    public String toString() {
        return "Zoo{" +
                "cat=" + cat +
                ", dog=" + dog +
                ", monkey=" + monkey +
                '}';
    }
}
