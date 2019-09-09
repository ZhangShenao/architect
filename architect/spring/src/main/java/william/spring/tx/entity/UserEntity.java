package william.spring.tx.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/12/7 11:52
 * @Description:
 */
@Getter
@Setter
@ToString
public class UserEntity /*implements IEntity*/{
    private static final String TABLE_NAME = "t_user";
    private static final String KEY_PROPERTY = "id";

    private long id;
    private String userName;
    private String realName;
    private int sex;
    private String mobile;
    private String email;
    private String note;
    private long positionId;

    /*@Override
    public String tableName() {
        return TABLE_NAME;
    }

    @Override
    public String keyProperty() {
        return KEY_PROPERTY;
    }*/
}
