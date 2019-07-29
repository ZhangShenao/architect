package william.computerprinciple.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>使用FIFO置换策略的缓存 </p>
 *
 * @author ZhangShenao
 * @date 2018年4月12日
 */
public class FIFOCache<K, V> implements Cache<K, V> {
    //使用一个双向链表维护缓存元素
    private DoubleLinkedList<K, V> list;

    //记录缓存的容量
    private int capacity;

    //记录缓存中的数据项
    private Map<K, V> cache;

    public FIFOCache(int capacity) {
        this.capacity = capacity;
        this.list = new DoubleLinkedList<>(capacity);
        this.cache = new HashMap<>(capacity);
    }

    @Override
    public void put(K key, V value) {
        //如果缓存中已经存在该项,则直接用新值替换旧值
        if (cache.containsKey(key)) {
            list.updateValue(key, value);
            cache.put(key, value);
            return;
        }

        //缓存中不存在该项,尝试增加新值
        //如果缓存容量已满,则使用置换策略,删除链表中的头结点
        if (list.isFull()) {
            DoubleLinkedList<K, V>.Node node = list.removeFirst();
            cache.remove(node.key);
        }

        //插入新节点
        list.addLast(key, value);
        cache.put(key, value);
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }
}
