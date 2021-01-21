package william.oop.idgenerator;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.assertj.core.util.VisibleForTesting;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author zhangshenao
 * @Date 2021-01-21
 * @Description 随机 id 生成器
 */
@Slf4j
public class RandomIdGenerator implements LogTradeIdGenerator {
    @Override
    public String generate() throws IdGenerationFailureException {
        Optional<String> host;
        try {
            host = parseLastFieldFromHostName();
        } catch (UnknownHostException e) {
            throw new IdGenerationFailureException("Host Name is Empty!");
        }
        if (!host.isPresent()) {
            throw new IdGenerationFailureException("Host Name is Empty!");
        }
        long ts = System.currentTimeMillis();
        String randStr = generateRandomAlphameric(8);
        return String.format("%s-%d-%s", host.get(), ts, randStr);
    }

    /**
     * 解析当前服务所在的域名的最后一个字段
     */
    private Optional<String> parseLastFieldFromHostName() throws UnknownHostException {
        String host = InetAddress.getLocalHost().getHostName();
        return Optional.ofNullable(getLastSubstrSplittedByDot(host));
    }

    @VisibleForTesting
    protected String getLastSubstrSplittedByDot(String host) {
        if (StringUtils.isEmpty(host)) {
            throw new IllegalArgumentException("host is empty");
        }
        String[] tokens = host.split("\\.");
        String sub = tokens[tokens.length - 1];
        return sub;
    }


    /**
     * 生成指定长度的随机数
     *
     * @param length 随机数的长度
     * @return 随机数
     */
    @VisibleForTesting
    protected String generateRandomAlphameric(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException();
        }
        char[] randomChars = new char[length];
        int count = 0;
        while (count < length) {
            int maxAscii = 'z';
            int randomAscii = ThreadLocalRandom.current().nextInt(maxAscii);
            boolean isDigit = randomAscii >= '0' && randomAscii <= '9';
            boolean isUppercase = randomAscii >= 'A' && randomAscii <= 'Z';
            boolean isLowercase = randomAscii >= 'a' && randomAscii <= 'z';
            if (isDigit || isUppercase || isLowercase) {
                randomChars[count] = (char) (randomAscii);
                ++count;
            }
        }
        return new String(randomChars);
    }
}
