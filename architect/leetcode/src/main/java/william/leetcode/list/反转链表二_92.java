package william.leetcode.list;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/25 下午3:50
 * https://leetcode.cn/problems/reverse-linked-list-ii/
 */
public class 反转链表二_92 {
    //首先找到待反转部分链表的头结点,然后进行交换指针的操作
    //时间复杂度O(N) 仅需遍历一次链表
    //空间复杂度O(1)
    public ListNode reverseBetween(ListNode head, int left, int right) {
        //边界条件
        if (head == null || head.next == null) {
            return head;
        }
        if (left >= right) {
            return head;
        }

        //借助哑头
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;

        //首先定位到待反转部分链表的头结点
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        //进行[left,right]范围内链表的反转
        ListNode cur = pre.next;
        for (int i = left; i < right; i++) {
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }

        //返回头结点
        return dummy.next;
    }
}
