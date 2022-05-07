package william.leetcode.list;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/4/24 上午10:17
 * https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class 反转链表_206 {
    //使用prev、cur、next三个指针,对链表进行遍历,并反转指针指向
    //时间复杂度O(n)
    //空间复杂度O(1)
    public ListNode reverseList(ListNode head) {
        //边界条件
        if (head == null || head.next == null) {
            return head;
        }

        //使用prev、cur、next三个指针
        ListNode prev = null;
        ListNode cur = head;

        //遍历链表,修改指针指向
        while (cur != null) {
            ListNode next = cur.next;   //记录next节点
            cur.next = prev;    //反转指针

            //进行下一轮遍历
            prev = cur;
            cur = next;
        }

        //最终cur指向null,prev指向链表新头节点
        return prev;
    }
}
