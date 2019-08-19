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
        Server server = NettyServerBuilder.forPort(8080).addService(new HelloServiceImpl()).build();
        server.start();
        System.err.println("server startup at 8080");
        server.awaitTermination();
    }
}
