package william.leetcode.list;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/5 上午9:08
 *
 * 链表节点
 */
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
