package william.leetcode;

import java.util.Stack;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/12 17:29
 * @Description:https://leetcode.com/problems/palindrome-linked-list/
 */
public class Solution234 {

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null){
            return true;
        }
        //通过一个栈,将所有节点入队
        Stack<ListNode> stack = new Stack<>();
        ListNode node = head;
        while (node != null){
            stack.push(node);
            node = node.next;
        }

        //依次比较
        ListNode left = head;
        int len = stack.size();

        for (int i = 0;i < len / 2;i++){
            ListNode right = stack.pop();
            if (left.val != right.val){
                return false;
            }
            left = left.next;
        }

        return true;
    }

}
