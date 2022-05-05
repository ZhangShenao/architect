package william.leetcode.list;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/5 上午9:15
 * <p>
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 */
public class 删除排序链表中的重复元素_83 {
    //时间复杂度O(n)
    //空间复杂度O(1)
    public ListNode deleteDuplicates(ListNode head) {
        //边界条件
        if (head == null || head.next == null) {
            return head;
        }

        //遍历链表,删除重复元素
        ListNode n = head;
        while (n != null) {
            //删除重复元素
            while (n.next != null && n.val == n.next.val) {
                n.next = n.next.next;
            }

            n = n.next;
        }

        //返回链表头结点
        return head;
    }
}
