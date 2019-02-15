package william.amq.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import william.amq.ActiveMQConfiguration;
import william.amq.reply.Producer;
import william.amq.reply.RequestDto;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/12 19:01
 * @Description:
 */
@SpringBootTest(classes = ActiveMQConfiguration.class)
@RunWith(SpringRunner.class)
public class TestRequestResponse {
    @Autowired
    private Producer producer;

    @Test
    public void testRequestResponse(){
        for (int i = 0;i < 10;i++){
            RequestDto request = new RequestDto();
            request.setUid("" + i);
            request.setPayload("payload-" + i);
            producer.sendMessage(request);
        }
    }
}
