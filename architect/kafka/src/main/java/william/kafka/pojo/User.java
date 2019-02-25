package william.kafka.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/25 14:20
 * @Description:
 */
@Getter
@Setter
@ToString
public class User {
    private String firstName;
    private String lastName;
    private int age;
    private String address;
}
