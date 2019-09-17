package william.jvm.serialization;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * @Author zhangshenao
 * @Date 2019-09-17
 * @Description
 */
@Data
@ToString
public class User implements Serializable {
    /**
     * 序列化版本号
     * 当程序检测到序列化和反序列化时的版本号不匹配时,会抛出InvalidClassException异常,这是一种安全性校验
     * serialVersionUID可以手动指定
     * 如果不手动指定,编译器会根据class进行特定的摘要算法,自动生成一个serialVersionUID。这个自动生成的版本号在每次类有修改时都会改变
     */
    static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private String address;
}
