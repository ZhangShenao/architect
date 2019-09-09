package william.leetcode;

import java.util.LinkedList;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/21 16:14
 * @Description:https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
 */
public class Solution109 {
    public TreeNode sortedListToBST(ListNode head) {
        //首先将链表转换为数组
        int[] nums = list2Arr(head);

        if (nums == null || nums.length == 0){
            return null;
        }
        return transform(nums, 0, nums.length - 1);
    }

    //将链表转换为数组
    private int[] list2Arr(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode node = head;
        LinkedList<Integer> nums = new LinkedList<>();
        while (node != null) {
            nums.add(node.val);
            node = node.next;
        }

        int[] arr = new int[nums.size()];

        for (int i = 0; i < nums.size(); i++) {
            arr[i] = nums.get(i);
        }

        return arr;

    }

    //递归构造二叉树
    private TreeNode transform(int[] nums, int start, int end) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(nums[start]);
        }

        //选取该范围内的中间节点作为当前子树的根
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        //然后依次在左、右两个范围内递归生成
        root.left = transform(nums, start, mid - 1);
        root.right = transform(nums, mid + 1, end);
        return root;
    }
}
