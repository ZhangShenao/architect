package william.netty.protobuf.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import william.netty.protobuf.ProtoMessages;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/16 18:42
 * @Description:
 */
public class ClientHandler extends SimpleChannelInboundHandler<ProtoMessages.Protos> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ProtoMessages.Protos msg) {
        System.err.println(msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        //向服务器端发送Protobuf消息
        ProtoMessages.Protos personProto = ProtoMessages.Protos.newBuilder()
                .setMessageType(ProtoMessages.Protos.MessageType.PERSON)
                .setPerson(ProtoMessages.Person.newBuilder().setName("James").setAge(34).setAddress("Los Angeles").build())
                .build();
        ctx.writeAndFlush(personProto);

        ProtoMessages.Protos studentProto = ProtoMessages.Protos.newBuilder()
                .setMessageType(ProtoMessages.Protos.MessageType.STUDENT)
                .setStudent(ProtoMessages.Student.newBuilder().setName("Jack").setAge(34).setSchool("Best School").build())
                .build();
        ctx.writeAndFlush(studentProto);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }
}
