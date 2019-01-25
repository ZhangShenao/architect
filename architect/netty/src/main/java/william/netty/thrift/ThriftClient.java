package william.netty.thrift;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import william.netty.generated.Person;
import william.netty.generated.PersonService;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/20 18:03
 * @Description:Thrift客户端
 */
public class ThriftClient {
    public static void main(String[] args) throws Exception {
        TTransport transport = new TFramedTransport(new TSocket("localhost",8888),600);
        TProtocol protocol = new TCompactProtocol(transport);
        PersonService.Client client = new PersonService.Client(protocol);
        transport.open();
        Person person1 = client.getPersonByName("James");
        System.err.println(person1);

        Person person2 = new Person();
        person2.setName("Kobe");
        person2.setAge(38);
        person2.setMarried(true);
        client.savePerson(person2);
    }
}
