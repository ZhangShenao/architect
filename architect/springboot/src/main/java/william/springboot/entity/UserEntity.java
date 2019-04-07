package william.springboot.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/10 17:20
 * @Description:
 */
@Getter
@Setter
@Table(name = "user")
@ToString
public class UserEntity {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    private String name;
    private int age;
}
