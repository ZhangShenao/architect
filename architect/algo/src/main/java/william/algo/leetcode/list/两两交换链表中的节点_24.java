package william.algo.leetcode.list;

/**
 * @Author zhangshenao
 * @Date 2021-10-30
 * @Description
 */
public class 两两交换链表中的节点_24 {
    public ListNode swapPairs(ListNode head) {
        //递归终止条件
        if (head == null || head.next == null) {
            return head;
        }


        //递归算法
        ListNode newHead = head.next;   //新的头结点是原始链表的第二个节点
        //原链表的头结点是新链表的第二个节点
        head.next = swapPairs(newHead.next);    //原始链表中其余节点的头节点是newHead.next，对其余节点进行交换
        newHead.next = head;
        return newHead;

    }
}
