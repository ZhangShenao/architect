package william.grpc;

import io.grpc.stub.StreamObserver;
import william.grpc.protos.GreeterGrpc;
import william.grpc.protos.GreeterOuterClass.HelloReply;
import william.grpc.protos.GreeterOuterClass.HelloRequest;

/**
 * @author zhangshenao
 * Created on 2019-08-19
 */
public class HelloServiceImpl extends GreeterGrpc.GreeterImplBase {
    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}
