package william.netty.test;

import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/27 13:05
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        int threads = Math.max(1, SystemPropertyUtil.getInt(
                "io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
        System.err.println(threads);
    }
}
