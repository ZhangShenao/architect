package william.leetcode.list;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/4/24 上午9:50
 * <p>
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/description/
 */
public class 删除链表的倒数第N个结点_19 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        //边界条件
        if (head == null || n <= 0) {
            return head;
        }

        //使用front、behind两个指针,front始终比behind超前n个节点,这样当front遍历到链表尾部时,front刚好指向倒数第n个节点
        ListNode front = head;
        ListNode dummy = new ListNode(0, head);  //借助哑结点
        ListNode behind = dummy;

        for (int i = 0; i < n; i++) {
            front = front.next;
        }

        //将front遍历到链表末尾,最终behind指向倒数第n个节点的前驱节点
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
