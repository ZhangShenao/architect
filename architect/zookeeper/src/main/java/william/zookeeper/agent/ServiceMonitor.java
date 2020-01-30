package william.zookeeper.agent;

import static william.zookeeper.constant.ZookeeperConstant.CONNECTION_TIMEOUT_MILLIS;
import static william.zookeeper.constant.ZookeeperConstant.ZOOKEEPER_URL;

import org.I0Itec.zkclient.ZkClient;

/**
 * @Author zhangshenao
 * @Date 2020-01-30
 * @Description 服务监控中心
 */
public class ServiceMonitor {
    private static final String BASE_PATH = "/agents";

    public void start() {
        //连接到ZK服务器
        ZkClient zkClient = new ZkClient(ZOOKEEPER_URL, CONNECTION_TIMEOUT_MILLIS);

        createBaseNodeIfNeeded(zkClient);

        //监听服务实例状态变化
        zkClient.subscribeChildChanges(BASE_PATH, (parentPath, currentChilds) -> {
            currentChilds.forEach(c -> {
                String path = parentPath + "/" + c;
                System.err.println("服务实例变更: " + path);
                zkClient.subscribeDataChanges(path, new ServiceInstanceInfoListener());
            });
        });
    }

    private void createBaseNodeIfNeeded(ZkClient zkClient) {
        if (!zkClient.exists(BASE_PATH)) {
            zkClient.createPersistent(BASE_PATH);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ServiceMonitor monitor = new ServiceMonitor();
        monitor.start();
        System.err.println("服务监控中心启动");
        Thread.sleep(Long.MAX_VALUE);
    }
}
