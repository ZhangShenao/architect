package william.ds.map;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/4 12:17
 * @Description:Map接口
 */
public interface Map<K, V> {
    V put(K key, V value);

    V get(K key);

    boolean containsKey(K key);

    boolean containsValue(V value);

    V remove(K key);

    int size();

    boolean isEmpty();

    void clear();
}
