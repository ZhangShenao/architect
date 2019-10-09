package william.netty.quickstart;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/16 13:01
 * @Description:
 */
public class SimpleHttpServerInitializer extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        //获取Pipeline
        ChannelPipeline pipeline = socketChannel.pipeline();

        //向Pipeline添加Http服务端编解码器
        pipeline.addLast("httpServerCodeC",new HttpServerCodec());

        //向Pipeline添加自定义的处理器
        pipeline.addLast("simpleHttpServerHandler",new SimpleHttpServerHandler());
    }
}
