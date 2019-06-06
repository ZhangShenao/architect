package william.ds.bst;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/6 13:34
 * @Description:符号表
 */
public interface SymbolTable<K extends Comparable<K>,V> {
    /**
     * 插入key-value对,如果key已经存在,则使用新的value覆盖旧的
     */
    void insert(K key, V value);

    /**
     * 获取key对应的value值
     */
    V  get(K key);

    /**
     * 删除key对应的value值
     */
    void remove(K key);

    /**
     * 判断当前key是否存在
     */
    boolean contains(K key);

    /**
     * 判断符号表是否为空
     */
    boolean isEmpty();

    /**
     * 获取最大的key
     */
    K min();

    /**
     * 获取最小的key
     */
    K max();

    /**
     * 删除最大的key
     */
    void removeMin();

    /**
     * 删除最小的key
     */
    void removeMax();
}
