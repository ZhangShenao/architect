package william.interview.collection.lru;

import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 基于LinkedHashMap可以实现一个LRU缓存
 */
public class LRUCache<K, V> {
    private final int capacity;   //缓存容量
    private final LinkedHashMap<K, V> cache;   //内部维护LinkedHashMap

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>(capacity);
    }

    public void put(K key,V value){
        //如果key已经存在,则将key移动到链表尾部
        cache.remove(key);

        //如果容量已达上限,则移除链表头部元素
        if (cache.size() >= capacity){
            K first = cache.keySet().iterator().next();
            cache.remove(first);
        }

        //插入新元素
        cache.put(key, value);
    }

    public Optional<V> get(K key){
        return Optional.ofNullable(cache.get(key));
    }

    public String traversal(){
        return cache.keySet().stream().map(Object::toString).collect(Collectors.joining(","));
    }

    public static void main(String[] args) {
        LRUCache<String,String> lruCache = new LRUCache<>(3);
        lruCache.put("A","A");
        lruCache.put("B","B");
        lruCache.put("C","C");
        System.out.println(lruCache.traversal());

        lruCache.put("D","D");
        lruCache.put("A","A");
        lruCache.put("D","D");
        System.out.println(lruCache.traversal());
    }
}
