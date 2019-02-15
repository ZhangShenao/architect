package william.amq.reply;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/12 18:33
 * @Description:响应消息
 */
@Getter
@Setter
@ToString
public class ResponseDto {
    //业务id
    private String uid;

    //处理结果
    private boolean success;
}
