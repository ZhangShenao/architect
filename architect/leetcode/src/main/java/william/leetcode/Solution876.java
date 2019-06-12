package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/12 17:48
 * @Description:https://leetcode.com/problems/middle-of-the-linked-list/
 */
public class Solution876 {
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        //遍历链表,获取链表长度

        ListNode node = head;
        int len = 0;
        while (node != null) {
            ++len;
            node = node.next;
        }

        //返回第(len / 2 + 1)个节点,需遍历len/2次
        int times = len / 2;
        node = head;
        for (int i = 0; i < times; i++) {
            node = node.next;
        }
        return node;
    }
}
