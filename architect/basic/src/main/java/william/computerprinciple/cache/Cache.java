package william.computerprinciple.cache;

/**
 * <p>缓存接口 </p>
 *
 * @author ZhangShenao
 * @date 2018年4月12日
 */
public interface Cache<K, V> {
    void put(K key, V value);

    V get(K key);
}
