package william.leetcode.list;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/4/24 上午10:36
 * <p>
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/description/
 */
public class 合并两个有序链表_21 {
    //采用双指针遍历两个有序链表
    //时间复杂度O(m+n)
    //空间复杂读O(1)
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //边界条件
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        ListNode l1 = list1;
        ListNode l2 = list2;
        ListNode dummy = new ListNode(0, null);  //借助dummy哑头
        ListNode n = dummy;

        //遍历两个链表,尾插入较小的元素
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                n.next = l1;
                l1 = l1.next;
            } else {
                n.next = l2;
                l2 = l2.next;
            }
            n = n.next;
        }

        //处理其中一个链表未遍历完的情况
        if (l1 != null) {
            n.next = l1;
        }
        if (l2 != null) {
            n.next = l2;
        }

        //返回最终结果
        return dummy.next;
    }
}
