package william.algo.leetcode.tree;

/**
 * @Author zhangshenao
 * @Date 2021-11-06
 * @Description https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
 * 采用递归算法：分别计算左、右子树的最大深度，然后取其中的最小值
 * 时间复杂度O(n)
 */
public class 二叉树的最小深度_111 {
    public int minDepth(TreeNode root) {
        //递归终止条件
        if (root == null) {
            return 0;
        }

        //分别计算左右子树的最小深度,然后去其中的最大值
        //需要注意左子树或右子树为空的情况
        if (root.left == null) {
            return 1 + minDepth(root.right);
        }

        if (root.right == null) {
            return 1 + minDepth(root.left);
        }

        int left = minDepth(root.left);
        int right = minDepth(root.right);

        return 1 + Math.min(left, right);

    }
}
