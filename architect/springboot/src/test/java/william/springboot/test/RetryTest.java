package william.springboot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import william.springboot.Application;
import william.springboot.service.impl.RetryServiceImpl;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/10 19:45
 * @Description:重试策略
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class RetryTest {
    @Autowired
    private RetryServiceImpl retryService;

    @Test
    public void testRetry() throws Exception {
        retryService.invoke();
    }
}
