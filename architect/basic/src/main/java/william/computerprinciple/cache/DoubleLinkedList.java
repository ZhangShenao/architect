package william.computerprinciple.cache;

/**
 * <p>双向链表 </p>
 *
 * @author ZhangShenao
 * @date 2018年4月12日
 */
public class DoubleLinkedList<K, V> {
    //头结点
    private Node head;

    //尾节点
    private Node tail;

    //记录链表的容量
    private int capacity;

    //记录链表的节点数量
    private int size;

    public DoubleLinkedList(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    /**
     * 向链表头部插入节点
     */
    public void addFirst(K key, V value) {
        //向空链表插入
        if (isEmpty()) {
            addToEmpty(key, value);
        }

        //向非空链表插入
        Node node = new Node(key, value);
        node.next = head;
        head.prev = node;
        head = node;
        ++size;
    }

    /**
     * 向链表尾部插入
     */
    public void addLast(K key, V value) {
        //向空链表插入
        if (isEmpty()) {
            addToEmpty(key, value);
        }

        Node node = new Node(key, value);
        node.prev = tail;
        tail.next = node;
        tail = node;
        ++size;
    }

    /**
     * 删除头结点
     */
    public Node removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node node = head;

        //处理删除后链表为空
        if (node.next == null) {
            head = null;
            tail = null;
        } else {
            head = node.next;
            head.prev = null;
        }
        --size;
        return node;
    }

    /**
     * 删除尾结点
     */
    public Node removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node node = tail;

        //处理删除后链表为空
        if (node.prev == null) {
            head = null;
            tail = null;
        } else {
            tail = node.prev;
            tail.next = null;
        }
        --size;
        return tail;
    }

    /**
     * 删除指定key的节点
     */
    public Node remove(K key) {
        //找到待删除的节点
        Node node = head;
        Node removed = null;
        while (node != null) {
            if (node.key.equals(key)) {
                removed = node;
                break;
            }
            node = node.next;
        }
        if (removed == null) {
            return null;
        }

        //头结点删除
        if (removed == head) {
            return removeFirst();
        }

        //尾结点删除
        if (removed == tail) {
            removeLast();
        }

        //普通节点删除
        Node prev = removed.prev;
        Node next = removed.next;
        prev.next = next;
        next.prev = prev;
        return removed;
    }

    /**
     * 更新节点的value值
     */
    public void updateValue(K key, V value) {
        Node node = head;
        while (node != null) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
            node = node.next;
        }
    }

    /**
     * 获取链表长度
     */
    public int size() {
        return size;
    }

    /**
     * 判断链表是否已满
     */
    public boolean isFull() {
        return (size == capacity);
    }

    /**
     * 判断链表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        Node node = head;
        while (node != null) {
            builder.append(node.toString());
            node = node.next;
            if (node != null) {
                builder.append(" -> ");
            }
        }
        return builder.append("]").toString();
    }

    /**
     * 向空链表中插入
     */
    private void addToEmpty(K key, V value) {
        Node node = new Node(key, value);
        head = node;
        tail = node;
        ++size;
    }

    public class Node {
        K key;
        V value;
        Node prev;
        Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }

        @Override
        public String toString() {
            return "{" + key + " , " + value + "}";
        }
    }
}
