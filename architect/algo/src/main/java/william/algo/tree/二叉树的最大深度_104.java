package william.algo.tree;

/**
 * @Author zhangshenao
 * @Date 2021-11-06
 * @Description https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 * 采用递归算法：分别计算左、右子树的最大深度，然后取其中的最大值
 * 时间复杂度O(n)
 */
public class 二叉树的最大深度_104 {
    public int maxDepth(TreeNode root) {
        //递归终止条件
        if (root == null) {
            return 0;
        }

        //分别计算左右子树的最大深度,然后去其中的最大值
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return 1 + Math.max(left, right);
    }
}
