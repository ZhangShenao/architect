package william.leetcode.list;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/27 下午1:05
 * <p>
 * https://leetcode.cn/problems/merge-k-sorted-lists/
 */
public class 合并K个升序链表_23 {
    //利用分治-递归思想
    //类似归并排序的思想
    //将链表数组拆分成左、右两部分,分别对左、右两部分进行合并,然后再将两个有序链表合并
    //时间复杂度O(kn*logk)
    //空间复杂度O(logk) 递归使用到的栈空间
    public ListNode mergeKLists(ListNode[] lists) {
        //边界条件
        if (lists == null || lists.length == 0) {
            return null;
        }


        //分治+递归
        return merge(lists, 0, lists.length - 1);
    }

    //对链表数组lists的[start,end]范围进行合并
    private ListNode merge(ListNode[] lists, int start, int end) {
        //递归终止条件
        if (start >= end) {
            return lists[start];
        }

        //将链表数组拆分成左、右两部分,分别对两部分进行合并,然后再将两个有序链表合并
        int mid = start + ((end - start) >> 1);
        ListNode left = merge(lists, start, mid);
        ListNode right = merge(lists, mid + 1, end);

        //返回合并后的头结点
        return mergeTwoSortedList(left, right);
    }

    //对两个有序链表进行合并,返回合并后的头结点
    private ListNode mergeTwoSortedList(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(0, null);   //借助哑头
        ListNode node = dummy;

        //遍历两个有序链表,进行合并
        while (left != null & right != null) {
            if (left.val <= right.val) {
                node.next = left;
                left = left.next;
            } else {
                node.next = right;
                right = right.next;
            }
            node = node.next;
        }

        //处理剩余一个链表没有遍历完的情况
        if (left != null) {
            node.next = left;
        }
        if (right != null) {
            node.next = right;
        }

        //返回合并后的头结点
        return dummy.next;
    }
}
