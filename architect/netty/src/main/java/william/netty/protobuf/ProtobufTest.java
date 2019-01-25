package william.netty.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/19 18:02
 * @Description:
 */
public class ProtobufTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        //通过Protobuf生成的API创建消息对象
        ProtoMessages.Protos proto = ProtoMessages.Protos.newBuilder()
                .setMessageType(ProtoMessages.Protos.MessageType.PERSON)
                .setPerson(ProtoMessages.Person.newBuilder().setName("James").setAge(34).setAddress("Los Angeles").build())
                .build();
        System.err.println(proto);

        //将消息对象序列化成字节数组
        byte[] bytes = proto.toByteArray();

        //通过字节数组反序列化消息对象
        ProtoMessages.Protos protos1 = ProtoMessages.Protos.parseFrom(bytes);
        System.err.println(protos1);
    }
}
