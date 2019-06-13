package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/13 13:35
 * @Description:https://leetcode.com/problems/merge-k-sorted-lists/
 */
public class Solution23 {
    public ListNode mergeKLists(ListNode[] lists) {
        //新链表的头结点
        ListNode head = null;

        //当前遍历到的新链表的节点
        ListNode node = null;

        //找到当前最小的节点
        int minIdx = -1;

        while ((minIdx = findMinIndex(lists)) >= 0) {
            ListNode minNode = lists[minIdx];
            if (head == null) {
                head = minNode;
            } else if (node == null) {
                node = minNode;
                head.next = node;
            } else {
                node.next = minNode;
                node = node.next;
            }
            lists[minIdx] = lists[minIdx].next;
        }

        return head;
    }

    private int findMinIndex(ListNode[] lists) {
        int minIdx = -1;
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] == null){
                continue;
            }
            if (minIdx < 0 || lists[i].val < lists[minIdx].val) {
                minIdx = i;
            }
        }
        return minIdx;
    }


}
