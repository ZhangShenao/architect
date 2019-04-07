package william.springboot.service.impl;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeoutException;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/10 19:33
 * @Description:重试
 */
@Service
public class RetryServiceImpl {
    //配置重试策略
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 2000L, multiplier = 5))
    public void invoke() throws Exception {
        System.err.println("执行了invoke方法...");
        throw new TimeoutException("RPC调用超时!!");
    }

    //重试失败后,最终的补偿方法
    @Recover
    public void recover(Exception e) {
        System.err.println("最终重试失败: " + e.getMessage() + "。进行补偿...");
    }
}
