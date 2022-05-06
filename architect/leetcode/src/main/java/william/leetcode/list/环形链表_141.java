package william.leetcode.list;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/4/24 上午9:29
 * <p>
 * https://leetcode-cn.com/problems/linked-list-cycle/description/
 */
public class 环形链表_141 {
    //使用快、慢两个指针
    //快指针每次前进两步,慢指针每次前进一步
    //如果两个指针相遇,则说明链表有环
    //时间复杂度O(n)
    //空间复杂度O(1)
    public boolean hasCycle(ListNode head) {
        //边界条件
        if (head == null || head.next == null) {
            return false;
        }

        //快慢指针
        ListNode fast = head;
        ListNode slow = head;

        while (slow.next != null && fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }

        //遍历到null.说明链表无环
        return false;
    }

}


