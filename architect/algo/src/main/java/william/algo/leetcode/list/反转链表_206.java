package william.algo.leetcode.list;

/**
 * @Author zhangshenao
 * @Date 2021-10-30
 * @Description
 */
public class 反转链表_206 {
    public ListNode reverseList(ListNode head) {
        //边界条件
        if (head == null || head.next == null) {
            return head;
        }

        //记录当前节点和前继节点
        ListNode prev = null;   //这里注意,尾指针要指向null
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}
