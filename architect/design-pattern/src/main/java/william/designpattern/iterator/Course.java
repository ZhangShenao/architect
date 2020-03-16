package william.designpattern.iterator;

import lombok.Data;
import lombok.ToString;

/**
 * @Author zhangshenao
 * @Date 2020-03-16
 * @Description
 */
@Data
@ToString
public class Course {
    private long id;

    private String name;

    public Course(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
