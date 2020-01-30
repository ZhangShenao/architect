package william.zookeeper.agent;

import static java.util.concurrent.TimeUnit.SECONDS;
import static william.zookeeper.constant.ZookeeperConstant.CONNECTION_TIMEOUT_MILLIS;
import static william.zookeeper.constant.ZookeeperConstant.ZOOKEEPER_URL;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;

import org.I0Itec.zkclient.ZkClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Author zhangshenao
 * @Date 2020-01-30
 * @Description 服务实例
 */
public class ServiceInstance {
    private static final String BASE_PATH = "/agents";

    private static final String AGENT_PATH = BASE_PATH + "/node";

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);


    public void start() {
        //连接到ZK服务器
        ZkClient zkClient = new ZkClient(ZOOKEEPER_URL, CONNECTION_TIMEOUT_MILLIS);

        //创建父节点
        createBaseNodeIfNeeded(zkClient);

        //创建服务节点——临时顺序节点
        String path = zkClient.createEphemeralSequential(AGENT_PATH, buildAgentInfo());

        //定时上报节点信息
        scheduledExecutorService
                .scheduleWithFixedDelay(() -> zkClient.writeData(path, buildAgentInfo()), 0L, 5L, SECONDS);
    }

    private void createBaseNodeIfNeeded(ZkClient zkClient) {
        if (!zkClient.exists(BASE_PATH)) {
            zkClient.createPersistent(BASE_PATH);
        }
    }

    private String buildAgentInfo() {
        ObjectMapper objectMapper = new ObjectMapper();
        InstanceInfo info = new InstanceInfo();
        info.setIp("192.168.1.1");
        info.setCpuRatio(ThreadLocalRandom.current().nextInt(100) + "%");
        info.setMemory(ThreadLocalRandom.current().nextInt(1000) + "M");
        info.setDisk(ThreadLocalRandom.current().nextInt(1000) + "M");
        try {
            return objectMapper.writeValueAsString(info);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ServiceInstance serviceInstance = new ServiceInstance();
        serviceInstance.start();
        System.err.println("服务节点启动");
    }
}
