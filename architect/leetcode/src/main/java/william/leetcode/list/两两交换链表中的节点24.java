package william.leetcode.list;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/4/24 上午10:47
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/description/
 */
public class 两两交换链表中的节点24 {
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

    public ListNode swapPairs(ListNode head) {
        //边界条件
        if (head == null || head.next == null) {
            return head;
        }

        //将原链表拆分成奇、偶两个链表,然后创建一个新的链表,按照先偶后奇的次序插入,最后返回新链表
        ListNode oddDummy = new ListNode(0, null);
        ListNode evenDummy = new ListNode(0, null);
        ListNode odd = oddDummy;
        ListNode even = evenDummy;
        ListNode cur = head;
        int idx = 1;
        while (cur != null) {
            if (idx % 2 != 0) {
                odd.next = cur;
                odd = odd.next;
            } else {
                even.next = cur;
                even = even.next;
            }
            cur = cur.next;
            idx++;
        }

        odd.next = null;
        even.next = null;

        //合并奇偶两个链表
        ListNode dummy = new ListNode(0, null);
        cur = dummy;
        idx = 0;
        odd = oddDummy.next;
        even = evenDummy.next;
        while (odd != null && even != null) {
            if (idx % 2 == 0) {
                cur.next = even;
                even = even.next;
            } else {
                cur.next = odd;
                odd = odd.next;
            }
            cur = cur.next;
            ++idx;
        }

        while (odd != null) {
            cur.next = odd;
            odd = odd.next;
            cur = cur.next;
        }

        while (even != null) {
            cur.next = even;
            even = even.next;
            cur = cur.next;
        }

        return dummy.next;
    }
}
