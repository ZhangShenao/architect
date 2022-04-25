package william.interview.algo.list;

import javax.swing.*;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/4/24 上午10:36
 * <p>
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/description/
 */
public class 合并两个有序链表21 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //边界条件
        if (list1 == null && list2 == null) {
            return null;
        }
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        ListNode dummy = new ListNode(0, null);  //创建哑结点
        ListNode cur = dummy;
        ListNode left = list1;
        ListNode right = list2;

        //遍历两个链表,寻找较小节点进行尾插入
        while (left != null && right != null) {
            if (left.val <= right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }

        //处理未遍历完的情况
        while (left != null) {
            cur.next = left;
            left = left.next;
            cur = cur.next;
        }
        while (right != null) {
            cur.next = right;
            right = right.next;
            cur = cur.next;
        }

        //返回头结点
        return dummy.next;
    }
}
