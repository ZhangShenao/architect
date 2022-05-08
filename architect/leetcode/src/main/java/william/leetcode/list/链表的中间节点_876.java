package william.leetcode.list;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/8 下午12:22
 * <p>
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 */
public class 链表的中间节点_876 {
    //使用快慢两个指针,对链表进行遍历
    //快指针每次前进两步,慢指针每次前进一步
    //当快指针移动到链表末尾时,慢指针就指向了链表的中间节点
    //时间复杂度O(n) 仅需遍历一次链表
    //空间复杂度O(1)
    public ListNode middleNode(ListNode head) {
        //边界条件
        if (head == null || head.next == null) {
            return head;
        }

        //使用快、慢指针
        ListNode fast = head;
        ListNode slow = head;

        //将快指针遍历到链表莫问
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        //最终慢指针指向链表中间节点
        return slow;
    }
}
