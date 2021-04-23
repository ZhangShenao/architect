package william.algo;

import java.util.Optional;

/**
 * @Author zhangshenao
 * @Date 2021-04-23
 * @Description 散列表实现
 */
public class HashTable<K, V> {
    private static final int DEFAULT_CAPACITY = 16; //默认容量

    private static final float LOAD_FACTOR = 0.75F; //装载因子

    private Entry<K, V>[] table;  //使用数组保存散列表元素

    private int size;   //散列表实际容量
    private int capacity;   //散列表容量

    public HashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.table = (Entry<K, V>[]) new Object[capacity];
        this.capacity = capacity;
        this.size = 0;
    }

    public HashTable() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 插入元素
     *
     * @param k Key
     * @param v Value
     */
    public void put(K k, V v) {
        //计算Hash值
        int idx = hash(k);

        //如果桶中元素为空,则直接插入
        if (table[idx] == null) {
            table[idx] = new Entry<>(k, v, null);
            ++size;

            //扩容
            if (size > capacity * LOAD_FACTOR) {
                resize();
            }
            return;
        }

        //桶只元素不为空,说明出现了散列冲突,采用链表法,将新增元素插入链表末尾
        Entry<K, V> e = table[idx];
        do {
            //Key重复,直接覆盖
            if (e.k.equals(k)) {
                e.v = v;
                return;
            }
            e = e.next;
        }
        while (e.next != null);
        e.next = new Entry<>(k, v, null);
        ++size;
        //扩容
        if (size > capacity * LOAD_FACTOR) {
            resize();
        }
    }

    /**
     * 根据Key查找散列表中的元素
     *
     * @param k Key
     * @return 查找的元素Value, 如果找不到则返回Optional.empty()
     */
    public Optional<V> get(K k) {
        int idx = hash(k);
        if (table[idx] == null) {
            return Optional.empty();
        }
        Entry<K, V> e = table[idx];
        while (e != null) {
            if (e.k.equals(k)) {
                return Optional.of(e.v);
            }
        }

        return Optional.empty();
    }

    /**
     * 删除指定Key的元素
     *
     * @param k Key
     */
    public void remove(K k) {
        int idx = hash(k);
        if (table[idx] == null) {
            return;
        }
        if (table[idx].k.equals(k)) {
            table[idx] = null;
            --size;
            return;
        }
        Entry<K, V> e = table[idx];
        while (e.next != null) {
            if (e.next.k.equals(k)) {
                e.next = e.next.next;
                --size;
                return;
            }
            e = e.next;
        }
    }

    /**
     * 散列函数
     *
     * @param k Key
     * @return 散列值
     */
    private int hash(K k) {
        int h;
        return (k == null) ? 0 : ((h = k.hashCode()) ^ (h >>> 16)) % table.length;
    }

    /**
     * 散列表扩容,扩容后容量为原来的2倍
     */
    private void resize() {
        Entry<K, V>[] tmp = (Entry<K, V>[]) new Object[capacity];

        //遍历原数组,将原数组中元素重新hash,插入新数组中
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                continue;
            }

            Entry<K, V> e = table[i];
            while (e.next != null) {
                int idx = hash(e.k);
                if (tmp[idx] == null) {
                    tmp[idx] = new Entry<>(e.k, e.v, null);
                } else {
                    tmp[idx].next = new Entry<>(e.k, e.v, tmp[idx]);
                }
                e = e.next;
            }
        }

        //返回新数组
        this.table = tmp;
    }


    /**
     * 散列表的节点
     *
     * @param <K> Key类型
     * @param <V> Value类型
     */
    private static class Entry<K, V> {
        private K k;

        private V v;

        private Entry<K, V> next;

        public Entry(K k, V v, Entry<K, V> next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }
    }
}
