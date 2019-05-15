package william.pattern;

/**
 * @Author: ZhangShenao
 * @Date: 2019/5/15 11:08
 * @Description:
 */
public interface CacheService {
    Data loadDataFromCache(String key);

    void putData(String key,Data data);

    void evictData(String key);
}
