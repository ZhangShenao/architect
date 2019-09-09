package william.springboot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/10 18:03
 * @Description:
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("age")
    private int age;
}
