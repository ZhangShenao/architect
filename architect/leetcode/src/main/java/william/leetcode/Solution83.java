package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/11 16:56
 * @Description:https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 */
public class Solution83 {
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        //记录上一次访问的节点
        ListNode lastNode = head;
        ListNode node = head.next;

        while (node != null) {
            //如果当前节点值与上一次的相等,则删除当前节点
            if (node.val == lastNode.val) {
                lastNode.next = node.next;
            }else {
                lastNode = node;
            }
            node = node.next;
        }
        return head;
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
