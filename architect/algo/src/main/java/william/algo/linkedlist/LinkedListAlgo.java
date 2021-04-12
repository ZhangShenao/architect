package william.algo.linkedlist;

/**
 * @Author zhangshenao
 * @Date 2021-04-12
 * @Description 链表常用算法
 */
public class LinkedListAlgo {
    /**
     * 单链表反转
     *
     * @param head 待反转的链表的首结点
     * @return 反转后的链表的首结点
     */
    public static <T> Node<T> reverse(Node<T> head) {
        //空链表或单节点链表直接返回
        if (head == null || head.next == null) {
            return head;
        }

        Node<T> prev = null;
        Node<T> cur = head;

        while (cur != null) {
            Node<T> next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }

    /**
     * 判断链表是否成环
     * 借助快、慢两个指针
     *
     * @param list 链表的首节点
     * @return 是否成环
     */
    public static <T> boolean hasCircle(Node<T> list) {
        if (list == null) {
            return false;
        }

        Node<T> slow = list;
        Node<T> fast = list.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    public static <T> void printAll(Node<T> list) {
        Node<T> p = list;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
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
        /*Node<Integer> head = new Node<>(1, null);
        Node<Integer> node = head;
        for (int i = 2; i < 10; i++) {
            node.next = new Node<>(i, null);
            node = node.next;
        }
        System.out.println("反转前: ");
        printAll(head);

        head = reverse(head);
        System.out.println("反转后: ");
        printAll(head);*/

        Node<Integer> head = new Node<>(1, null);
        Node<Integer> node = head;
        for (int i = 2; i < 10; i++) {
            node.next = new Node<>(i, null);
            node = node.next;
        }
        System.out.println("链表是否成环: " + hasCircle(head));

        node.next = head;
        System.out.println("链表是否成环: " + hasCircle(head));
    }
}
