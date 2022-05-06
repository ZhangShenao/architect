package william.leetcode.list;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/6 上午9:28
 * <p>
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 */
public class 相交链表_160 {
    //方法一:借助Hash表,先遍历链表A,在Hash表中保存所有节点
    //然后再遍历链表B,依次判断每个节点在Hash表中是否存在,如果找到了即为相交的节点
    //时间复杂度O(m+n)
    //空间复杂度O(n)
//    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//        //边界条件
//        if (headA == null || headB == null) {
//            return null;
//        }
//
//        //首先遍历链表A,并在Hash表中保存所有节点
//        Set<ListNode> cache = new HashSet<>();
//        ListNode n = headA;
//        while (n != null) {
//            cache.add(n);
//            n = n.next;
//        }
//
//        //之后遍历链表B,依次判断每个节点是否在Hash表中存在,如果存在则为相交节点
//        n = headB;
//        while (n != null) {
//            if (cache.contains(n)) {
//                return n;
//            }
//            n = n.next;
//        }
//
//        //无相交节点
//        return null;
//    }

    //方法二:使用两个指针pA和pB,分别遍历A、B两个链表
    //当遍历到链表尾部时,则交换链表,从另一个链表头开始遍历
    //如果最终两个指针相遇,则就是链表相交点
    //如果最后两个指针均为null,则说明无相交点
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //边界条件
        if (headA == null || headB == null) {
            return null;
        }

        //使用两个指针
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB) {  //循环终止条件:两指针相交,或两指针都为null
            pA = ((pA != null) ? pA.next : headB);
            pB = ((pB != null)) ? pB.next : headA;
        }
        return pA;
    }
}
