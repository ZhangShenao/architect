package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/11 16:38
 * @Description:https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 */
public class Solution19 {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null) {
            return null;
        }

        //首先遍历链表,获取链表的长度
        int len = 0;
        ListNode node = head;
        while (node != null) {
            ++len;
            node = node.next;
        }

        //计算出要删除的节点的索引
        int idx = len - n;

        //删除头结点
        if (idx == 0) {
            head = head.next;
            return head;
        }

        //找到前置节点
        node = head;
        for (int i = 0; i < idx - 1; i++) {
            node = node.next;
        }

        //删除节点
        node.next = node.next.next;
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
