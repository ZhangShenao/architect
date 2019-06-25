package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/24 17:51
 * @Description:https://leetcode.com/problems/add-two-numbers/
 */
public class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }

        //保存进位信息
        int remain = 0;
        ListNode root = null;
        ListNode node = null;

        //依次将l1、l2的两个值相加
        while (l1 != null && l2 != null) {
            //处理进位
            int sum = l1.val + l2.val + remain;
            remain = sum / 10;
            sum = sum % 10;

            if (root == null) {
                root = new ListNode(sum);
            } else if (node == null) {
                node = new ListNode(sum);
                root.next = node;
            } else {
                node.next = new ListNode(sum);
                node = node.next;
            }

            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int sum = l1.val + remain;
            sum = sum % 10;
            remain = sum / 10;

            if (root == null) {
                root = new ListNode(sum);
            } else if (node == null) {
                node = new ListNode(sum);
                root.next = node;
            } else {
                node.next = new ListNode(sum);
                node = node.next;
            }

            l1 = l1.next;
        }

        while (l2 != null) {
            int sum = l2.val + remain;
            sum = sum % 10;
            remain = sum / 10;

            if (root == null) {
                root = new ListNode(sum);
            } else if (node == null) {
                node = new ListNode(sum);
                root.next = node;
            } else {
                node.next = new ListNode(sum);
                node = node.next;
            }

            l2 = l2.next;
        }

        //处理多一位的情况
        if (remain > 0) {
            if (root == null) {
                root = new ListNode(remain);
            } else if (node == null) {
                node = new ListNode(remain);
                root.next = node;
            } else {
                node.next = new ListNode(remain);
                node = node.next;
            }
        }
        return root;

    }
}
