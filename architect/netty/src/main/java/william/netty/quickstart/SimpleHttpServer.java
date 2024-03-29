package william.netty.quickstart;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/16 12:53
 * @Description:使用Netty实现的简单的Http服务器
 */
public class SimpleHttpServer {
    public static void main(String[] args) {
        /*//创建2个线程组
        //Boss线程组:只负责接收客户端连接,具体的逻辑分发给workerGroup处理
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        //Worker线程组:负责实际处理客户端连接
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //创建ServerBootstrap 服务端启动引导器
            ServerBootstrap serverBootstrap = new ServerBootstrap()
                    //设置线程组
                    .group(bossGroup, workerGroup)
                    //设置Channel类型
                    .channel(NioServerSocketChannel.class)
                    //设置自定义Initializer,通过Initializer添加自定义处理器
                    .childHandler(new SimpleHttpServerInitializer());

            //服务器启动,并绑定指定端口
            ChannelFuture future = serverBootstrap.bind(8080).sync();
            System.err.println("SimpleHttpServer Start");

            //注册服务器关闭监听,等待服务器关闭
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //优雅地关闭线程组
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }*/

        Set<Integer> s1 = new HashSet<>();
        s1.add(1);
        s1.add(2);
        s1.add(3);
        s1.add(4);
        s1.add(5);

        Set<Integer> s2 = new HashSet<>();
        s2.add(2);
        s2.add(4);
        s2.add(6);

        System.err.println(s1.retainAll(s2));
        System.err.println(s1);
    }
}
