package william.kafka.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import william.kafka.pojo.User;
import java.util.Map;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/25 14:21
 * @Description:自定义序列化器,用于序列化User对象
 */
public class UserSerializer implements Serializer<User>{
    private ObjectMapper objectMapper;

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        //创建序列化所必要的资源
        objectMapper = new ObjectMapper();
    }

    @Override
    public byte[] serialize(String topic, User data) {
        try {
            return objectMapper.writeValueAsString(data).getBytes("utf-8");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        //资源释放
        objectMapper = null;
    }
}
