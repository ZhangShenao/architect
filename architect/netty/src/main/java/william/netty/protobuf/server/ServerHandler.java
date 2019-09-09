package william.netty.protobuf.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import william.netty.protobuf.ProtoMessages;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/16 18:42
 * @Description:
 */
public class ServerHandler extends SimpleChannelInboundHandler<ProtoMessages.Protos> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ProtoMessages.Protos msg) {
        System.err.println("Receive Protobuf Message: " + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.err.println("Channel Active");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.err.println("Channel Active");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
