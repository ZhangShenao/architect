package william.netty.socket.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import william.netty.socket.server.ServerHandler;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/16 18:36
 * @Description:
 */
public class ClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("lengthFieldBasedFrameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4))
                .addLast("lengthFieldPrepender", new LengthFieldPrepender(4))
                .addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8))
                .addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8))
                .addLast("clientHandler",new ClientHandler());
    }
}
