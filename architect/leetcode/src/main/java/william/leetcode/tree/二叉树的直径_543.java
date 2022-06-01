package william.leetcode.tree;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/6/1 上午9:46
 * https://leetcode.cn/problems/diameter-of-binary-tree/
 */
public class 二叉树的直径_543 {
    //记录全局最大直径
    private int maxDiameter = 0;

    //使用递归计算最大深度
    //记录全局最大直径
    //以root为根的二叉树的最大直径,等于左子树的最大深度+右子树的最大深度
    //时间复杂度O(N) 需要遍历一次二叉树
    //空间复杂度O(N) 递归所需的栈空间
    public int diameterOfBinaryTree(TreeNode root) {
        //边界条件
        if (root == null) {
            return 0;
        }

        //递归计算最大深度
        maxDepth(root);

        //返回最大直径
        return maxDiameter;
    }

    //递归计算最大深度
    private int maxDepth(TreeNode root) {
        if (root == null) {  //递归终止条件
            return 0;
        }

        //分别计算左、右子树的最大深度
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        //最大直径=左子树的最大深度+右子树的最大深度
        maxDiameter = Math.max(left + right, maxDiameter);

        //返回左、右子树深度的最大值+1
        return Math.max(left, right) + 1;
    }
}
