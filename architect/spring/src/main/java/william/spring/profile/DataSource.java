package william.spring.profile;

import lombok.Data;
import lombok.ToString;

/**
 * @Author zhangshenao
 * @Date 2019-12-25
 * @Description
 */
@Data
@ToString
public class DataSource {
    private String url;
    private String driverClass;
    private String userName;
    private String password;

    private String stage;
}
