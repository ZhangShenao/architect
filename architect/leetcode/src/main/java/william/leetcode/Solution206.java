package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/12 14:57
 * @Description:https://leetcode.com/problems/reverse-linked-list/
 */
public class Solution206 {
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }

        //记录当前节点、前驱节点和后继节点
        ListNode prev = head;
        ListNode cur = head.next;
        ListNode post = null;

        while (cur != null){
            post = cur.next;
            cur.next = prev;
            prev = cur;
            cur = post;
        }

        head.next = null;
        head = prev;
        return head;
    }
}
