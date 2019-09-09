package william.kafka.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import william.kafka.pojo.User;
import java.io.IOException;
import java.util.Map;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/16 11:54
 * @Description:自定义反序列化机制
 */
public class UserDeserializer implements Deserializer<User> {
    private ObjectMapper objectMapper;

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        //创建反序列化所必要的资源
        objectMapper = new ObjectMapper();
    }

    @Override
    public User deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, User.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void close() {
        objectMapper = null;
    }
}
