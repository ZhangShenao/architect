package william.leetcode.list;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/26 上午10:27
 * https://leetcode.cn/problems/lru-cache/
 */
public class LRU缓存_146 {
    //基于map+双向链表实现
    //map用于保存缓存项,基于hash算法,可以实现O(1)时间复杂度的缓存操作
    //使用双向链表维护缓存项访问顺序,每次缓存插入都从链表头部插入,访问时将缓存项移动到链表头部。
    //当缓存容量超过上限时,移除链表尾部元素
    //时间复杂度O(1)
    //空间复杂度O(N) map+链表节点
    private static class LRUCache {
        private int capacity;   //缓存容量
        private int size;   //缓存中元素数量
        private Map<Integer, LRUNode> cache; //缓存元素
        private LRUNode head;   //链表头结点(空头)
        private LRUNode tail;   //链表尾节点(空尾)

        public LRUCache(int capacity) {
            if (capacity < 1) {
                throw new IllegalArgumentException("capacity must be positive");
            }

            //初始化缓存参数
            this.capacity = capacity;
            this.size = 0;
            this.cache = new HashMap<>(capacity);

            //初始化链表头、尾节点
            this.head = new LRUNode();
            this.tail = new LRUNode();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            //获取缓存节点
            LRUNode node = cache.get(key);
            if (node == null) {  //key不存在
                return -1;
            }

            //将节点移动到链表头部
            moveToHead(node);

            //返回缓存值
            return node.val;
        }

        public void put(int key, int value) {
            //获取缓存节点
            LRUNode node = cache.get(key);
            if (node != null) {
                //key已经存在,更新value值
                node.val = value;

                //将节点移动到链表头部
                moveToHead(node);
                return;
            }

            //如果缓存容量已达上限,需要删除链表末尾节点
            if (size == capacity) {
                LRUNode removed = removeLast();
                cache.remove(removed.key);  //删除缓存项
                --size;
            }

            //在链表头部插入新节点
            node = addFirst(key, value);
            cache.put(key, node);    //保存缓存元素
            ++size;
        }

        //将节点移动到链表头部
        private void moveToHead(LRUNode node) {
            LRUNode prev = node.prev;
            LRUNode next = node.next;
            prev.next = next;
            next.prev = prev;
            LRUNode first = head.next;
            head.next = node;
            node.prev = head;
            node.next = first;
            first.prev = node;
        }

        //删除链表末尾节点,返回已经删除的节点
        private LRUNode removeLast() {
            LRUNode node = tail.prev;
            LRUNode prev = node.prev;
            prev.next = tail;
            tail.prev = prev;
            return node;
        }

        //在链表头部插入
        private LRUNode addFirst(int key, int value) {
            LRUNode node = new LRUNode();
            node.key = key;
            node.val = value;
            LRUNode prev = head.next;
            head.next = node;
            node.prev = head;
            node.next = prev;
            prev.prev = node;
            return node;
        }
    }

    //LRU缓存链表节点
    //双向链表
    private static class LRUNode {
        private int key;
        private int val;
        private LRUNode prev;
        private LRUNode next;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
    }
}
