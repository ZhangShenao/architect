package william.leetcode.tree;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/6/1 上午9:54
 * <p>
 * https://leetcode.cn/problems/binary-tree-maximum-path-sum/
 */
public class 叉树中的最大路径和_124 {
    private int maxPath = Integer.MIN_VALUE;

    //采用递归计算
    //记录全局最大路径和
    //以root为根的二叉树的最大路径和=左子树最大路径和+右子树最大路径和
    //时间复杂度O(N) 需要遍历一次二叉树
    //空间复杂度O(N) 递归所需栈空间
    public int maxPathSum(TreeNode root) {
        //边界条件
        if (root == null) {
            return 0;
        }

        //递归计算
        maxPath(root);

        //返回最大路径和
        return maxPath;
    }

    //递归计算最大路径和
    private int maxPath(TreeNode root) {
        if (root == null) {
            return 0;
        }

        //递归计算左、右子树的最大路径和
        //如果左、右子树的最大路径和为负数,则直接丢弃
        int left = Math.max(0, maxPath(root.left));
        int right = Math.max(0, maxPath(root.right));

        //保存全局最大路径和
        maxPath = Math.max(maxPath, left + right + root.val);

        //返回左、右子树的最大路径和+根节点值
        return Math.max(left, right) + root.val;
    }
}
