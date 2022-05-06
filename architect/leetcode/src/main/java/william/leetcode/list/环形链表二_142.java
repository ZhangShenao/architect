package william.leetcode.list;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/4/24 上午9:38
 * <p>
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/description/
 */
public class 环形链表二_142 {
    //使用快、慢两个指针
    //快指针每次前进2步,慢指针每次前进1步
    //先通过快、慢指针能否相遇判断链表是否存在环
    //如果存在环,则将慢指针重置为链表头结点
    //之后,快、慢指针开始一起移动,每次前进1步,最终指针相遇处即为链表入环的起点
    //时间复杂度(n)
    //空间复杂度O(1)
    public ListNode detectCycle(ListNode head) {
        //边界条件
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        //使用快、慢指针,判断链表是否有环
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null && slow.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                break;
            }
        }

        if (fast != slow) { //链表无环
            return null;
        }

        //将慢指针重置回头结点
        slow = head;

        //快慢指针同时移动,每次移动一步,最终的相遇点即为链表入环的起点
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
