package william.netty.thrift;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import william.netty.generated.PersonService ;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/20 17:58
 * @Description:Thrift 服务器端
 */
public class ThriftServer {
    public static void main(String[] args) throws TTransportException {
        //新建Socket
        TNonblockingServerSocket socket = new TNonblockingServerSocket(8888);
        THsHaServer.Args arg = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);
        PersonService.Processor<PersonServiceHandler> processor = new PersonService.Processor<>(new PersonServiceHandler());

        //协议层
        arg.protocolFactory(new TCompactProtocol.Factory());

        //传输层
        arg.transportFactory(new TFramedTransport.Factory());

        arg.processorFactory(new TProcessorFactory(processor));
        THsHaServer server = new THsHaServer(arg);
        System.err.println("Thrift Server Started!!");
        server.serve();
    }
}
