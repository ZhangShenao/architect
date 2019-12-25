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
    @Value("${name}")
    private String name;

    @Value("${age}")
    private int age;
}
