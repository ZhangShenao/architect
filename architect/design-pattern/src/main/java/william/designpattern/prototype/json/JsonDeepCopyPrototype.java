package william.designpattern.prototype.json;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.ToString;

/**
 * @Author zhangshenao
 * @Date 2020-03-08
 * @Description 基于json方式实现对象的深拷贝
 */
@Data
@ToString
public class JsonDeepCopyPrototype implements Cloneable {
    private String id;

    private String name;

    private List<String> alias;

    //浅拷贝方式不变
    @Override
    public JsonDeepCopyPrototype clone() {
        try {
            return (JsonDeepCopyPrototype) super.clone();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //基于json实现深拷贝
    public JsonDeepCopyPrototype deepCopy() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(this);

            return objectMapper.readValue(json, JsonDeepCopyPrototype.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
