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

    /**
     * 合并两个有序链表，生成的新链表仍然保证有序
     *
     * @param list1 有序链表1
     * @param list2 有序链表2
     * @return 合并后的有序链表
     */
    public static <T extends Comparable<T>> Node<T> mergeTwoSortedList(Node<T> list1, Node<T> list2) {
        if (list1 == null && list2 == null) {
            throw new IllegalArgumentException("Linked List must not be null!");
        }


        Node<T> cur = new Node<>();
        Node<T> merged = cur;   //利用哨兵简化编程难度

        Node<T> node1 = list1;
        Node<T> node2 = list2;

        while (node1 != null && node2 != null) {
            if (node1.data.compareTo(node2.data) < 0) {
                cur.next = new Node<>(node1.data, null);
                node1 = node1.next;
            } else {
                cur.next = new Node<>(node2.data, null);
                node2 = node2.next;
            }
            cur = cur.next;
        }

        while (node1 != null) {
            cur.next = new Node<>(node1.data, null);
            node1 = node1.next;
            cur = cur.next;
        }

        while (node2 != null) {
            cur.next = new Node<>(node2.data, null);
            node2 = node2.next;
            cur = cur.next;
        }

        return merged.next;
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

       /* Node<Integer> head = new Node<>(1, null);
        Node<Integer> node = head;
        for (int i = 2; i < 10; i++) {
            node.next = new Node<>(i, null);
            node = node.next;
        }
        System.out.println("链表是否成环: " + hasCircle(head));

        node.next = head;
        System.out.println("链表是否成环: " + hasCircle(head));*/

        Node<Integer> list1 = new Node<>(2, null);
        Node<Integer> node1 = list1;
        for (int i = 4; i < 20; i++) {
            if (i % 2 == 1) {
                continue;
            }
            node1.next = new Node<>(i, null);
            node1 = node1.next;
        }
        System.out.println("List1: ");
        printAll(list1);

        Node<Integer> list2 = new Node<>(1, null);
        Node<Integer> node2 = list2;
        for (int i = 3; i < 50; i++) {
            if (i % 2 == 0) {
                continue;
            }
            node2.next = new Node<>(i, null);
            node2 = node2.next;
        }
        System.out.println("List2: ");
        printAll(list2);

        Node<Integer> merged = mergeTwoSortedList(list1, list2);

        System.out.println("Merged: ");
        printAll(merged);
    }
}
