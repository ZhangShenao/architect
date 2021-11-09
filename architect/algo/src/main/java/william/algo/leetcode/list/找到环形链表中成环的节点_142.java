package william.algo.leetcode.list;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author zhangshenao
 * @Date 2021-10-30
 * @Description
 */
public class 找到环形链表中成环的节点_142 {
    public ListNode detectCycle(ListNode head) {
        //边界判断
        if (head == null || head.next == null) {
            return null;
        }

        //遍历链表,将所有节点保存在HashSet中,借助HashSet进行重复性判断
        Set<ListNode> nodes = new HashSet<>();
        ListNode cur = head;
        while (cur != null) {
            if (nodes.contains(cur)) {
                return cur;
            }
            nodes.add(cur);
            cur = cur.next;
        }

        return null;
    }
}
