package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/10 15:55
 * @Description:https://leetcode.com/problems/balanced-binary-tree/
 */
public class Solution110 {
    public boolean isBalanced(TreeNode root) {
        return isBalancedRecursive(root);
    }

    private boolean isBalancedRecursive(TreeNode root){
        if (root == null){
            return true;
        }
        if (root.left == null && root.right == null){
            return true;
        }

        //判断当前树是否平衡
        int depthL = depth(root.left);
        int depthR = depth(root.right);
        if (Math.abs(depthL - depthR) > 1){
            return false;
        }

        //判断当前根节点的左、右子树是否平衡
        return isBalanced(root.left) && isBalanced(root.right);
    }

    //递归计算每个节点的深度
    private int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(depth(node.left), depth(node.right));
    }

}
