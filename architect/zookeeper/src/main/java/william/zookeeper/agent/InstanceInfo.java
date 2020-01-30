package william.zookeeper.agent;

import lombok.Data;
import lombok.ToString;

/**
 * @Author zhangshenao
 * @Date 2020-01-30
 * @Description 服务实例相关信息
 */
@Data
@ToString
public class InstanceInfo {
    private String ip;
    private String cpuRatio;
    private String memory;
    private String disk;
}
