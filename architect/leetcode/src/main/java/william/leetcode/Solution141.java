package william.leetcode;

import java.util.HashSet;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/11 10:15
 * @Description:https://leetcode.com/problems/linked-list-cycle/
 */
public class Solution141 {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        //使用一个Hash表保存访问过的节点
        HashSet<ListNode> hash = new HashSet<>();

        //遍历链表,判断每个节点之前是否被访问过,如果之前被访问过,则说明链表有环
        ListNode node = head;
        while (node != null){
            if (hash.contains(node)){
                return true;
            }
            hash.add(node);
            node = node.next;
        }

        return false;
    }
}
