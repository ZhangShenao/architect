package william.netty.websocket.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import william.netty.constant.Constants;

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
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline()
                                    .addLast("httpServerCodec", new HttpServerCodec())
                                    .addLast("chunkedWriteHandler", new ChunkedWriteHandler())
                                    //HttpObjectAggregator，将多个数据段聚合成一个完整的HttpObject
                                    .addLast("httpObjectAggregator", new HttpObjectAggregator(4096))
                                    //WebSocket服务端处理器
                                    .addLast("", new WebSocketServerProtocolHandler("/ws"))
                                    .addLast("textWebSocketFrameHandler", new TextWebSocketFrameHandler());

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
