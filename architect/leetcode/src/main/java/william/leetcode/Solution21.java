package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/13 12:49
 * @Description:https://leetcode.com/problems/merge-two-sorted-lists/
 */
public class Solution21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //新链表的头结点
        ListNode head = null;

        //当前遍历到的新链表的节点
        ListNode node = null;

        //遍历两个链表,每次增加最小的那个节点
        ListNode l = l1;
        ListNode r = l2;

        while (l != null && r != null) {
            ListNode min = null;
            if (r.val < l.val) {
                min = new ListNode(r.val);
                r = r.next;
            } else {
                min = new ListNode(l.val);
                l = l.next;
            }
            if (head == null) {
                head = min;
            } else if (node == null) {
                node = min;
                head.next = node;
            } else {
                node.next = min;
                node = node.next;
            }
        }

        while (l != null) {
            ListNode min = new ListNode(l.val);
            if (head == null) {
                head = min;
            } else if (node == null) {
                node = min;
                head.next = node;
            } else {
                node.next = min;
                node = node.next;
            }
            l = l.next;
        }

        while (r != null) {
            ListNode min = new ListNode(r.val);
            if (head == null) {
                head = min;
            } else if (node == null) {
                node = min;
                head.next = node;
            } else {
                node.next = min;
                node = node.next;
            }
            r = r.next;
        }

        return head;
    }

}
