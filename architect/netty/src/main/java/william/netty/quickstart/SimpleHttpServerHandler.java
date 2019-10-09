package william.netty.quickstart;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static io.netty.util.CharsetUtil.UTF_8;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/16 13:17
 * @Description: Http请求处理器
 */
public class SimpleHttpServerHandler extends SimpleChannelInboundHandler<HttpRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpRequest request) {
        System.err.println("请求方式: " + request.method().name());
        System.err.println("请求URI: " + request.uri());
        System.err.println("客户端地址: " + ctx.channel().remoteAddress());
        //通过ByteBuf构造响应内容
        ByteBuf content = Unpooled.copiedBuffer("Hello World", UTF_8);

        //使用FullHttpResponse构造HTTP Response对象
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, content);

        //设置响应头信息
        response.headers()
                .set(CONTENT_TYPE, "text/plain")
                .set(CONTENT_LENGTH, content.readableBytes());

        //写入响应数据
        ctx.writeAndFlush(response);

        //服务端响应数据后，主动关闭连接
        ctx.channel().close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.err.println("Channel Active");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        System.err.println("Channel Registered");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        System.err.println("Channel Read Complete");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.err.println("Channel Inactive");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
        System.err.println("Channel Unregistered");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        System.err.println("Handler Added");
    }
}
