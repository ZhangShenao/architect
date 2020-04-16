package william.spring.property;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;
import lombok.ToString;

/**
 * @Author zhangshenao
 * @Date 2019-12-25
 * @Description
 */
@Data
@ToString
public class Person {
    @Value("${firstName}")
    private String firstName;

    @Value("#{27+1}")
    private int age;

    @Value("${person.lastName}")
    private String lastName;
}
