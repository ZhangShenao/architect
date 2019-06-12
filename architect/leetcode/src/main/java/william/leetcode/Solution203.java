package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/12 14:46
 * @Description:https://leetcode.com/problems/remove-linked-list-elements/
 */
public class Solution203 {
    private static ListNode removeElements(ListNode head, int val) {
        //先处理头结点的删除
        while (head != null && head.val == val){
            head = head.next;
        }

        if (head == null){
            return head;
        }


        ListNode prev = head;
        ListNode node = head.next;
        while (node != null){
            if (node.val == val){
                prev.next = node.next;
            }else{
                prev = node;
            }
            node = node.next;
        }

        return head;
    }



    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
