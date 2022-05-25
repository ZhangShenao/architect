package william.leetcode.list;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/4/24 上午9:50
 * <p>
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/description/
 */
public class 删除链表的倒数第N个结点_19 {
    //采用双指针法:前、后指针
    //前指针向前进n步
    //然后前、后指针同时前进
    //当前指针移动到链表尾部时,后指针指向倒数第n+1个节点,直接删除倒数第n个节点即可
    //时间复杂度O(N) 仅需遍历一次链表
    //空间复杂度O(1)
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //边界条件
        if (head == null || n < 1) {
            return head;
        }

        //采用前、后双指针
        ListNode dummy = new ListNode(0, head);  //借助哑头
        ListNode front = head;
        ListNode behind = dummy;

        //前front指针先前进n步
        for (int i = 0; i < n; i++) {
            front = front.next;
        }

        //然后front、behind指针同时前进,当front移动到链表末尾时,behind就指向倒数第n+1个节点
        while (front != null) {
            front = front.next;
            behind = behind.next;
        }

        //删除倒数第n个节点
        behind.next = behind.next.next;

        //返回头结点
        return dummy.next;
    }
}
