package william.esjob;

import com.cxytiandi.elasticjob.annotation.EnableElasticJob;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/18 13:06
 * @Description:
 */
@SpringBootApplication
@EnableElasticJob
public class ElasticJobApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElasticJobApplication.class, args);
    }
}
