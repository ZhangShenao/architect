package william.designpattern.prototype.shallow;

import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 * @Author zhangshenao
 * @Date 2020-03-08
 * @Description 基于JDK Object提供的clone方法,实现对象的浅拷贝
 */
@Data
@ToString
public class ShallowCopyPrototype implements Cloneable {
    private String id;

    private String name;

    private List<String> alias;

    @Override
    public ShallowCopyPrototype clone(){
        try {
            return (ShallowCopyPrototype) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
