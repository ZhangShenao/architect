package william.grpc;

import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;

/**
 * @author zhangshenao
 * Created on 2019-08-19
 * gRpc服务端
 */
public class GRPCServer {
    public static void main(String[] args) throws Exception {
        //gRPC基于Netty4 和 HTTP2构建了Server
        Server server = NettyServerBuilder.forPort(8080)    //创建Netty HTTP/2 服务端
                .addService(new HelloServiceImpl())         //将需要调用的服务接口实现类注册到内部Registry中,可以根据RPC请求消息中的服务定义信息查询到服务接口实现类
                .build();       //创建gRPC Server,它是gRPC服务端的抽象,聚合了各种Listener,用于RPC消息的统一调度和处理

        //启动RPC Server
        server.start();
        System.err.println("server startup at 8080");
        server.awaitTermination();
    }
}
