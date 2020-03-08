package william.designpattern.prototype.deep;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 * @Author zhangshenao
 * @Date 2020-03-08
 * @Description 基于深拷贝实现的原型模式 深克隆方式: 1.序列化 2.json
 */
@Data
@ToString
public class DeepCopyPrototype implements Cloneable, Serializable {
    private String id;

    private String name;

    private List<String> alias;

    //浅拷贝方式不变
    @Override
    public DeepCopyPrototype clone() {
        try {
            return (DeepCopyPrototype) super.clone();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //基于序列化实现深拷贝
    public DeepCopyPrototype deepCopy() {
        try {
            //序列化
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            //反序列化
            byte[] bytes = bos.toByteArray();
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (DeepCopyPrototype) ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
