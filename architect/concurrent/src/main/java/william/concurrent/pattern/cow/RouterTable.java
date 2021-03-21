package william.concurrent.pattern.cow;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author zhangshenao
 * @Date 2021-03-19
 * @Description 路由表
 * 路由表是典型的读多写少的场景，每次RPC调用都需要调用路由表查询路由信息，从而进行负载均衡。但是路由表的修改相对来说频率较低。且路由信息可以容忍短暂的数据不一致。
 * 基于该场景，非常适合采用Copy-On-Write模式
 */
public class RouterTable {
    //维护路由信息
    //key=serviceName value=该serviceName下部署的所有实例的路由信息
    private final ConcurrentHashMap<String, CopyOnWriteArraySet<Router>> table = new ConcurrentHashMap<>();

    //根据接口名获取路由表
    public Set<Router> routeTable(String serviceName) {
        return table.get(serviceName);
    }

    //服务下线,剔除路由信息
    public void remove(Router router) {
        String serviceName = router.getServiceName();
        Optional.ofNullable(table.get(serviceName)).ifPresent(x -> x.remove(router));
    }

    //服务上线,注册路由信息
    public void add(Router router) {
        String serviceName = router.getServiceName();
        table.putIfAbsent(serviceName, new CopyOnWriteArraySet<>());
        table.get(serviceName).add(router);
    }

}
