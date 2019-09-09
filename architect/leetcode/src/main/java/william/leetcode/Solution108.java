package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/21 15:52
 * @Description:https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 */
public class Solution108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        return transform(nums, 0, nums.length - 1);
    }

    //递归构造二叉树
    private TreeNode transform(int[] nums, int start, int end) {
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
