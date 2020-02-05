package william.redis.optimize.bloomfilter;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @Author zhangshenao
 * @Date 2020-02-05
 * @Description 使用BloomFilter, 过滤掉请求不存在的id, 防止出现缓存穿透的情况
 */
public class ProductService {
    //BloomFilter
    //1000:期望存入的数据个数，0.001:期望的误差率
    private BloomFilter<String> bloomFilter =
            BloomFilter.create(Funnels.stringFunnel(Charset.forName("UTF8")), 1000, 0.001);

    //缓存
    private Map<String, String> cache = new HashMap<>();

    //数据库
    private Map<String, String> db = new HashMap<>();

    //初始化数据
    public void init() {
        bloomFilter.put("1");
        bloomFilter.put("2");
        bloomFilter.put("3");

        cache.put("1", "手机");
        cache.put("2", "平板电脑");
        cache.put("3", "篮球");

        db.put("1", "手机");
        db.put("2", "平板电脑");
        db.put("3", "篮球");
    }

    public String queryById(String productId) {
        //1. 通过布隆过滤器过滤掉无效id
        if (!bloomFilter.mightContain(productId)) {
            return "无效id";
        }

        //2. 尝试从缓存中获取
        String product = cache.get(productId);
        if (product != null) {
            return product;
        }

        //3. 缓存中不存在,从数据库中获取,并放入缓存中
        product = db.get(productId);
        Optional.ofNullable(product).ifPresent(p -> cache.put(productId, p));
        
        return product;
    }

}
