package william.leetcode.list;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/25 上午10:31
 * <p>
 * https://leetcode.cn/problems/add-two-numbers/
 */
public class 两数相加_2 {
    //从头开始依次遍历两个链表,对每个节点进行相加,并保存进位
    //借助哑头
    //考虑其中一个链表遍历完的情况
    //时间复杂度O(N) 对两个链表都进行一次遍历
    //空间复杂度O(1)
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //边界条件
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        int carry = 0;//保存进位
        ListNode dummy = new ListNode(0, null); //借助哑头
        ListNode n = dummy;

        //遍历两个链表,依次将每个节点相加
        while (l1 != null || l2 != null || carry > 0) {
            //相加两个节点
            int sum = 0;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            //加上进位
            sum += carry;

            //保存新节点
            int cur = sum % 10;
            carry = sum / 10;
            n.next = new ListNode(cur, null);
            n = n.next;
        }


        return dummy.next;
    }
}
