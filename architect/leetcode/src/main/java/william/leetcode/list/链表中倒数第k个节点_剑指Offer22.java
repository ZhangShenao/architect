package william.leetcode.list;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/8 下午12:28
 * https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
 */
public class 链表中倒数第k个节点_剑指Offer22 {
    //使用前后两个指针
    //首先使用前指针遍历链表,前进k-1次
    //然后前、后两个指针同时开始遍历,当前指针前进到链表尾部时,后指针就指向倒数第k个节点
    //时间复杂度O(n) 仅遍历一次链表
    //空间复杂度O(1)
    public ListNode getKthFromEnd(ListNode head, int k) {
        //边界条件
        if (head == null) {
            return null;
        }
        if (k <= 0) {
            return null;
        }

        //使用前、后两个指针,前指针先前进k-1步
        int step = k - 1;
        ListNode front = head;
        ListNode behind = head;
        for (int i = 0; i < step; i++) {
            front = front.next;
            if (front == null) { //边界条件
                return null;
            }
        }

        //然后同时移动前、后指针,当前指针移动到链表尾部时,后指针即指向链表倒数第k个节点
        while (front.next != null) {
            front = front.next;
            behind = behind.next;
        }

        return behind;

    }
}
