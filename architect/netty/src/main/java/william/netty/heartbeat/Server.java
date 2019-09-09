package william.netty.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import william.netty.constant.Constants;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/16 18:32
 * @Description:
 */
public class Server {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    //handler是交给bossGroup处理的,childHandler是交给workerGroup处理的
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline()
                                    //添加IdleStateHandler，进行心跳检测，如果客户端在指定时间内没有发生读/写/读写操作，则触发IdleStateEvent
                                    .addLast("idleStateHandler",new IdleStateHandler(10L,10L,10L, TimeUnit.SECONDS))
                                    .addLast("serverHandler",new ServerHandler());

                        }
                    });
            ChannelFuture future = serverBootstrap.bind(Constants.SERVER_PORT).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
