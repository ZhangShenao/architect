package william.interview.algo.list;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/4/24 上午9:38
 * <p>
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/description/
 */
public class 环形链表二142 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode detectCycle(ListNode head) {
        //边界条件
        if (head == null || head.next == null) {
            return null;
        }

        //使用快、慢两个指针,慢指针每次移动一步,快指针每次移动两步
        ListNode slow = head;
        ListNode fast = head;

        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {  //两指针相遇,说明链表成环
                break;
            }
        }

        if (slow != fast) {  //无环
            return null;
        }

        //链表成环,fast从头开始遍历,最终slow为入环点
        fast = head;
        while (slow != head) {
            slow = slow.next;
            head = head.next;
        }
        return slow;    //slow指针为入环点
    }
}
