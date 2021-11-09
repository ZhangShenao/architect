package william.algo.leetcode.list;

/**
 * @Author zhangshenao
 * @Date 2021-10-30
 * @Description
 */
public class 环形链表_141 {
    public boolean hasCycle(ListNode head) {
        //边界条件
        if (head == null || head.next == null) {
            return false;
        }

        //使用快慢指针
        ListNode fast = head.next;
        ListNode slow = head;

        while (fast != null && fast.next != null && slow != null) {
            if (fast == slow) {      //如果快慢指针相撞,则表示链表有环
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        //快慢指针未相撞,链表无环
        return false;
    }
}
