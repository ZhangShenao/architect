package william.netty.protobuf.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import william.netty.constant.Constants;
import william.netty.protobuf.ProtoMessages;

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
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline()
                                    //添加Protobuf相关Handler
                                    .addLast("protobufVarint32FrameDecoder", new ProtobufVarint32FrameDecoder())
                                    .addLast("protobufDecoder", new ProtobufDecoder(ProtoMessages.Protos.getDefaultInstance()))
                                    .addLast("protobufVarint32LengthFieldPrepender", new ProtobufVarint32LengthFieldPrepender())
                                    .addLast("protobufEncoder", new ProtobufEncoder())
                                    .addLast("serverHandler", new ServerHandler());
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
