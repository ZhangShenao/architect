package william.netty.chat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/16 18:42
 * @Description:
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {
    private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        Channel channel = ctx.channel();
        channels.forEach(c -> {
            if (c == channel) {
                c.writeAndFlush("[自己] - " + msg + "\n");
            } else {
                c.writeAndFlush("[" + channel.remoteAddress() + "] - 发送消息: " + msg + "\n");
            }
        });
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.err.println(ctx.channel().remoteAddress() + "上线");

        //广播通知其他客户端
        Channel channel = ctx.channel();

        channels.writeAndFlush("[服务器] - " + channel.remoteAddress() + ": 加入\n");

        //保存客户端连接
        channels.add(channel);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.err.println(ctx.channel().remoteAddress() + "下线");

        //广播通知其他客户端
        Channel channel = ctx.channel();
        channels.writeAndFlush("[服务器] - " + channel.remoteAddress() + ": 下线\n");

        //移除断开的客户端连接
//        channels.remove(channel);  此处可省略，ChannelGroup会自动移除断开的Channel
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
