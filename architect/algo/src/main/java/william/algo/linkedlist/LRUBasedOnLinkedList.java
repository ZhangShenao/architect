package william.algo.linkedlist;

import java.util.Optional;
import java.util.Scanner;

/**
 * @Author zhangshenao
 * @Date 2021-04-11
 * @Description 基于链表实现的LRU算法
 * 本实现为带头结点的链表,head指针的next指向链表的首元素
 */
public class LRUBasedOnLinkedList<T> {
    private static final int DEFAULT_CAPACITY = 10; //默认容量
    //头结点 head.next指向链表的首元素
    private Node<T> head;

    //链表长度(元素数量)
    private int size;

    //链表容量
    private int capacity;

    public LRUBasedOnLinkedList(int capacity) {
        this.head = new Node<>();
        this.head.next = null;
        this.size = 0;
        this.capacity = capacity;
    }

    public LRUBasedOnLinkedList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 插入元素
     *
     * @param data 元素值
     */
    public void add(T data) {
        //找到前驱节点
        Optional<Node<T>> prev = findPrev(data);

        if (prev.isPresent()) { //如果节点存在,则将改节点移动到链表头部
            //先删除当前节点
            removeNext(prev.get());

            //再在链表头部插入
            addFirst(data);
        } else { //如果节点不存在,则直接在头部插入
            //如果链表长度已达上限,则删除链表尾部的元素
            if (size == capacity) {
                removeLast();
            }
            addFirst(data);
        }
    }

    /**
     * 打印链表
     */
    public void printAll() {
        Node<T> node = head.next;
        while (node != null) {
            System.out.print(node.data + ",");
            node = node.next;
        }
        System.out.println();
    }

    /**
     * 删除一个前驱节点的后继节点
     *
     * @param prev 前驱节点
     */
    private void removeNext(Node<T> prev) {
        Node<T> tmp = prev.next;
        prev.next = tmp.next;
        tmp = null;
        --size;
    }

    /**
     * 在链表头部插入
     *
     * @param data 待插入的数据
     */
    private void addFirst(T data) {
        Node<T> first = head.next;
        head.next = new Node<>(data, first);
        ++size;
    }

    /**
     * 找到元素的前驱节点
     *
     * @param data 指定元素
     * @return 前驱节点
     */
    private Optional<Node<T>> findPrev(T data) {
        Node<T> node = head;
        while (node.next != null) {
            if (node.next.data.equals(data)) {
                return Optional.of(node);
            }
            node = node.next;
        }
        return Optional.empty();
    }

    /**
     * 删除链表尾节点
     */
    private void removeLast() {
        //空链表直接返回
        if (head.next == null) {
            return;
        }

        //找到倒数第二个元素
        Node<T> prev = head;
        while (prev.next.next != null) {
            prev = prev.next;
        }

        //删除倒数第二个元素的后一个元素
        Node<T> last = prev.next;
        prev.next = null;
        last = null;
        --size;
    }

    /**
     * 链表的节点
     */
    private static class Node<T> {
        //节点的数据
        private T data;

        //后继节点指针
        private Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public Node() {
        }
    }

    public static void main(String[] args) {
        LRUBasedOnLinkedList<Integer> lru = new LRUBasedOnLinkedList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            lru.add(scanner.nextInt());
            lru.printAll();
        }
    }
}
