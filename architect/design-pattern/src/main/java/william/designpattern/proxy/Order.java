package william.designpattern.proxy;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description
 */
@Getter
@Setter
@ToString
public class Order {
    private long id;
    private String info;
    private long createTime;
}
