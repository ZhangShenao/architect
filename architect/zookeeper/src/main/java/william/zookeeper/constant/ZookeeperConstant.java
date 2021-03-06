package william.zookeeper.constant;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/7 15:12
 * @Description:
 */
public interface ZookeeperConstant {
    //会话心跳超时时间
    int SESSION_TIMEOUT_MILLIS = 3000;

    //建立连接的超时时间
    int CONNECTION_TIMEOUT_MILLIS = 5000;

    //String ZOOKEEPER_URL = "Linux01:2181,Linux02:2181,Linux03:2181";
    String ZOOKEEPER_URL = "127.0.0.1:2181";
}
