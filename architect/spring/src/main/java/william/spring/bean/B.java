package william.spring.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Author: ZhangShenao
 * @Date: 2019/5/14 09:51
 * @Description:
 */
@Component
@Scope(scopeName = "prototype")
@Getter
@Setter
public class B {
    @Autowired
    private A a;

}
