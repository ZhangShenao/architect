package william.leetcode.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/7 上午9:19
 * <p>
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 */
public class 回文链表_234 {
    //首先遍历一次链表,将链表的全部节点一次保存到数组中
    //然后基于数组是否为回文数组
    //时间复杂度O(n)
    //空间复杂度O(n)
    public boolean isPalindrome(ListNode head) {
        //边界条件
        if (head == null) {
            return false;
        }
        if (head.next == null) {
            return true;
        }

        //遍历链表,将元素按顺序保存到List中
        List<Integer> list = new ArrayList<>();
        ListNode n = head;
        while (n != null) {
            list.add(n.val);
            n = n.next;
        }

        //判断list是否回文
        int l = 0;
        int r = list.size() - 1;
        while (l < r) {
            if (list.get(l).intValue() != list.get(r).intValue()) {
                return false;
            }
            ++l;
            --r;
        }
        return true;
    }
}
