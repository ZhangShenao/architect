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
 * gRpc客户端
 */
public class GRPCClient {
    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = NettyChannelBuilder.forAddress("127.0.0.1", 8080).usePlaintext(true).build();

        GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);

        HelloRequest request = HelloRequest.newBuilder().setName("123").build();
        HelloReply reply = stub.sayHello(request);

        System.err.println(reply.getMessage());

        channel.shutdown().awaitTermination(1, TimeUnit.SECONDS);
    }
}
