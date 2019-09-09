package william.netty.chat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import william.netty.constant.Constants;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/16 18:49
 * @Description:
 */
public class ChatClient {
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
                                    .addLast("delimiterBasedFrameDecoder", new DelimiterBasedFrameDecoder(4096, Delimiters.lineDelimiter()))
                                    .addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8))
                                    .addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8))
                                    .addLast("clientHandler", new ChatClientHandler());
                        }
                    });
            Channel channel = bootstrap.connect("127.0.0.1", Constants.SERVER_PORT).sync().channel();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            for (;;){
                String line = reader.readLine();
                channel.writeAndFlush(line+ "\r\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
