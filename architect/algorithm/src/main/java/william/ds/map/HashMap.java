package william.ds.map;

import java.util.Objects;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/4 12:21
 * @Description:哈希表
 */
public class HashMap<K, V> implements Map<K, V> {
    //默认容量,必须为2的n次方
    private static final int DEFAULT_CAPACITY = 1 << 4;

    //容量上限,必须为2的n次方
    private static final int MAXIMUM_CAPACITY = 1 << 30;

    //默认装载因子
    private static final float DEFAULT_LOAD_FACTOR = 0.75F;

    //记录表中元素的数量
    private int size;

    //桶式结构——链表数组
    private Entry<K, V>[] table;

    //装载因子,当哈希表中(元素数量/容量>装载因子)时,需要对哈希表进行rehash.
    private float loadFactor;

    //达到哈希表扩容的容量阈值
    private int threshold;


    public HashMap(int capacity, float loadFactor) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be positive!!");
        }
        if (capacity > MAXIMUM_CAPACITY) {
            capacity = MAXIMUM_CAPACITY;
        }
        if (loadFactor <= 0 || loadFactor > DEFAULT_LOAD_FACTOR) {
            throw new IllegalArgumentException("loadFactor must be in (0,0.75]!!");
        }
        this.table = (Entry<K, V>[]) new Object[capacity];
        this.loadFactor = loadFactor;
        this.size = 0;
        this.threshold = (int) (capacity * loadFactor);
    }

    public HashMap(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    public HashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    @Override
    public V put(K key, V value) {
        return putVal(key, value, hash(key));
    }

    @Override
    public V get(K key) {
        Entry<K, V> e = getEntry(key);
        return (e == null ? null : e.value);
    }

    @Override
    public boolean containsKey(K key) {
        return (get(key) != null);
    }

    @Override
    public boolean containsValue(V value) {
        for (int i = 0; i < table.length; i++) {
            Entry<K, V> e = table[i];
            while (e != null) {
                if (equalsValue(e.value, value)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public void clear() {
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
        size = 0;
    }

    private Entry<K, V> getEntry(K key) {
        int hash = hash(key);
        Entry<K, V> e = table[bucketForKey(key)];
        while (e != null) {
            if (e.hash == hash && equalsKey(e.key, key)) {
                return e;
            }
        }
        return null;
    }

    private V putVal(K k, V v, int hash) {
        //找到key所在的桶
        int bucket = bucketForKey(k);
        Entry<K, V> e = table[bucket];

        //如果桶为空,则创建新的Entry并返回
        if (e == null) {
            e = new Entry<>(k, v, hash, null);
            ++size;
            return null;
        }

        //如果桶中已存在该key,则更新value并返回旧value
        for (; ; ) {
            if (e.hash == hash && equalsKey(e.key, k)) {
                V value = e.value;
                e.value = v;
                return value;
            }
            if (e.next == null) {
                break;
            }
            e = e.next;
        }

        //桶中不存在该key,则新建一个Entry链接到桶末尾
        e.next = new Entry<>(k, v, hash, null);
        ++size;
        return null;
    }

    //哈希函数
    private static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    //定位到一个key所在的桶
    private final int bucketForKey(Object key) {
        int hash = hash(key);
        int len = table.length;
        return (len - 1) & hash;
    }

    private final boolean equalsKey(K k1, K k2) {
        if (k1 == null && k2 == null) {
            return true;
        }
        if (k1 != null && k1.equals(k2)) {
            return true;
        }
        return (k2 != null && k2.equals(k1));
    }

    private final boolean equalsValue(V v1, V v2) {
        if (v1 == null && v2 == null) {
            return true;
        }
        if (v1 != null && v1.equals(v2)) {
            return true;
        }
        return (v2 != null && v2.equals(v1));
    }

    //哈希表中的一个键值对,需要重写equals()和hashCode()方法
    private static class Entry<K, V> {
        final K key;
        V value;
        final int hash;
        Entry<K, V> next;

        public Entry(K key, V value, int hash, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = next;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry e = (Entry) obj;
            return (Objects.equals(key, e.key) && Objects.equals(value, e.value));
        }

        @Override
        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }
    }
}
