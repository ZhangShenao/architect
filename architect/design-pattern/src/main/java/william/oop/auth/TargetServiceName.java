package william.oop.auth;

/**
 * @Author zhangshenao
 * @Date 2021-01-09
 * @Description 请求目标服务名称, 针对 RPC 服务
 */
public class TargetServiceName implements RequestAddr {
    private String serviceName;

    public TargetServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String addr() {
        return serviceName;
    }
}
