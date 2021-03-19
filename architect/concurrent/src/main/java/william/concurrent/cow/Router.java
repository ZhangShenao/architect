package william.concurrent.cow;

import java.util.Objects;

import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2021-03-19
 * @Description 路由信息
 */
@Data
public class Router {
    private String serviceName; //服务名
    private String ip;  //ip
    private int port;   //port

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Router router = (Router) o;
        return port == router.port &&
                Objects.equals(serviceName, router.serviceName) &&
                Objects.equals(ip, router.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceName, ip, port);
    }
}
