package william.leetcode.list;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/4/24 上午9:29
 * <p>
 * https://leetcode-cn.com/problems/linked-list-cycle/description/
 */
public class 环形链表141 {
    public boolean hasCycle(ListNode head) {
        //边界条件
        if (head == null || head.next == null) {
            return false;
        }

        //使用快、慢两个指针,慢指针每次移动一步,快指针每次移动两步
        ListNode slow = head;
        ListNode fast = head;

        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            //如果两个指针相遇,则说明链表成环
            if (slow == fast) {
                return true;
            }
        }

        //遍历到了尾节点,说明链表无环
        return false;
    }


    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}


