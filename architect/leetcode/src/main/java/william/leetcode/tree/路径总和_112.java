package william.leetcode.tree;

/**
 * https://leetcode-cn.com/problems/path-sum/description/
 */
public class 路径总和_112 {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        //边界条件
        if (root == null) {
            return false;
        }

        //递归判断
        return hasPathSum(root, 0, targetSum);
    }


    private boolean hasPathSum(TreeNode root, int current, int targetSum) {
        //递归终止条件
        if (root == null) {
            return (current == targetSum);
        }

        //更新当前路径和
        current += root.val;
        //遍历到叶子节点,判断当前路径和
        if (root.left == null && root.right == null) {
            return (current == targetSum);
        }

        //递归判断左、右子树(注意如果子树为空则不认为是一条路径)
        if (root.left == null) {
            return hasPathSum(root.right, current, targetSum);
        }
        if (root.right == null) {
            return hasPathSum(root.left, current, targetSum);
        }

        //左、右子树均不为空的情况
        return hasPathSum(root.left, current, targetSum) || hasPathSum(root.right, current, targetSum);
    }
}
