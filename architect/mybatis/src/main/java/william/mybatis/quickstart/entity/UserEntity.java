package william.mybatis.quickstart.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/12/7 11:52
 * @Description:
 */
@Getter
@Setter
@ToString
public class UserEntity implements Serializable{
    private long id;
    private String userName;
    private String realName;
    private int sex;
    private String mobile;
    private String email;
    private String note;
    private Long positionId;
}
