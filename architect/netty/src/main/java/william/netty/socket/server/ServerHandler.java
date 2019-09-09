package william.netty.socket.server;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/16 18:42
 * @Description:
 */
@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<String> {
    private static final ServerHandler instance = new ServerHandler();

    public static ServerHandler getInstance() {
        return instance;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        System.err.println("客户端地址: " + ctx.channel().remoteAddress());
        System.err.println("接收到客户端消息: " + msg);
        ctx.writeAndFlush("Re: " + msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }

}
