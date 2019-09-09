package william.netty.protobuf.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import william.netty.constant.Constants;
import william.netty.protobuf.ProtoMessages;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/16 18:49
 * @Description:
 */
public class Client {
    public static void main(String[] args) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    //添加Protobuf相关Handler
                                    .addLast("protobufVarint32FrameDecoder", new ProtobufVarint32FrameDecoder())
                                    .addLast("protobufDecoder", new ProtobufDecoder(ProtoMessages.Protos.getDefaultInstance()))
                                    .addLast("protobufVarint32LengthFieldPrepender", new ProtobufVarint32LengthFieldPrepender())
                                    .addLast("protobufEncoder", new ProtobufEncoder())
                                    .addLast("clientHandler", new ClientHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect("127.0.0.1", Constants.SERVER_PORT).sync();
            future.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
