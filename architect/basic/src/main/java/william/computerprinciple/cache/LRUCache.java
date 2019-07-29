package william.computerprinciple.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>使用LRU置换策略的缓存 </p>
 *
 * @author ZhangShenao
 * @date 2018年4月12日
 */
public class LRUCache<K, V> implements Cache<K, V> {
    //使用一个双向链表维护缓存元素
    private DoubleLinkedList<K, V> list;

    //记录缓存的容量
    private int capacity;

    //记录缓存中的数据项
    private Map<K, V> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.list = new DoubleLinkedList<>(capacity);
        this.cache = new HashMap<>(capacity);
    }

    @Override
    public void put(K key, V value) {
        //如果该缓存项已存在
        if (cache.containsKey(key)) {
            cache.put(key, value);

            //在链表中删除key所在的节点,并将新节点插入链表头部
            list.remove(key);
            list.addFirst(key, value);
            return;
        }

        //如果该缓存项不存在
        //如果链表已满,则采用LRU置换策略,删除链表尾节点,并在链表头部插入新节点
        if (list.isFull()) {
            list.removeLast();
        }
        list.addFirst(key, value);
        cache.put(key, value);
    }

    @Override
    public V get(K key) {
        //如果缓存项中不存在,直接返回null
        V value = cache.get(key);
        if (value == null) {
            return null;
        }

        //更新链表,将该key对应的节点移动到链表头部
        list.remove(key);
        list.addFirst(key, value);
        return value;
    }
}
