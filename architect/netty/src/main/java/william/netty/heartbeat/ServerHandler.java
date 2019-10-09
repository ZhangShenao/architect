package william.netty.heartbeat;

import java.util.Optional;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/17 13:30
 * @Description:
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    //当事件被触发时，回调该方法
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (!(evt instanceof IdleStateEvent)) {
            return;
        }

        //检测IdleStateEvent事件，打印信息，并将客户端连接关闭
        IdleStateEvent event = (IdleStateEvent) evt;
        String eventMsg = null;
        switch (event.state()) {
            case READER_IDLE:
                eventMsg = "读空闲";
                break;
            case WRITER_IDLE:
                eventMsg = "写空闲";
                break;
            case ALL_IDLE:
                eventMsg = "读/写空闲";
                break;
        }
        Optional.of(eventMsg).ifPresent(msg -> System.err.println(ctx.channel().remoteAddress() + ": " + msg));
        ctx.channel().close();
    }
}
