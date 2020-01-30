package william.zookeeper.agent;

import java.io.IOException;

import org.I0Itec.zkclient.IZkDataListener;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Author zhangshenao
 * @Date 2020-01-30
 * @Description 监听服务实例状态变化
 */
public class ServiceInstanceInfoListener implements IZkDataListener {
    @Override
    public void handleDataChange(String dataPath, Object data) {
        String info = (String) data;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            System.err.println("服务实例 " + dataPath + ", 状态信息: " + objectMapper.readValue(info, InstanceInfo.class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void handleDataDeleted(String dataPath) {

    }
}
