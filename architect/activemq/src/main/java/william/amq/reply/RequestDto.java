package william.amq.reply;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/12 18:40
 * @Description:请求消息
 */
@Getter
@Setter
@ToString
public class RequestDto {
    //业务id
    private String uid;

    //业务数据
    private String payload;
}
