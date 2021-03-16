package william.grpc;

import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import io.grpc.netty.NettyChannelBuilder;
import william.grpc.protos.GreeterGrpc;
import william.grpc.protos.GreeterOuterClass.HelloReply;
import william.grpc.protos.GreeterOuterClass.HelloRequest;

/**
 * @author zhangshenao
 * Created on 2019-08-19
 * gRPC Client Stub
 */
public class GRPCClient {
    public static void main(String[] args) throws InterruptedException {
        //构建 Channel 连接
        ManagedChannel channel = NettyChannelBuilder
                .forAddress("127.0.0.1", 8080)
                .usePlaintext()
                .build();

        //构建 Client Stub 用与发送请求
        GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);

        //构造请求对象
        HelloRequest request = HelloRequest.newBuilder().setName("123").build();

        //发送请求,获取响应结果
        HelloReply reply = stub.sayHello(request);
        System.err.println(reply.getMessage());

        //关闭 Channel 连接
        channel.shutdown().awaitTermination(1, TimeUnit.SECONDS);
    }
}
