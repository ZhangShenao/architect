package william.leetcode.list;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/4/24 上午10:17
 * https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class 反转链表_206 {
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

    public ListNode reverseList(ListNode head) {
        //边界条件
        if (head == null || head.next == null) {
            return head;
        }

        //借助3个指针
        ListNode prev = head;
        ListNode cur = head.next;
        ListNode next = null;

        //遍历链表
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        //新的链表头结点为prev
        head.next = null;
        return prev;
    }
}
