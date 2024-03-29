package william.leetcode.list;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/4/24 上午10:08
 * <p>
 * https://leetcode-cn.com/problems/remove-linked-list-elements/description/
 */
public class 移除链表元素_203 {
    public ListNode removeElements(ListNode head, int val) {
        //边界条件
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(0, head);  //借助哑结点
        ListNode prev = dummy;
        ListNode current = head;

        //遍历链表,删除值为val的节点
        while (current != null) {
            if (current.val != val) {
                prev = prev.next;
                current = current.next;
                continue;
            }

            prev.next = current.next;
            current = current.next;
        }

        //返回头结点
        return dummy.next;
    }
}
