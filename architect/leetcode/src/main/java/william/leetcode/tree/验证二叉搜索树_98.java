package william.leetcode.tree;

/**
 * https://leetcode-cn.com/problems/validate-binary-search-tree/description/
 */
public class 验证二叉搜索树_98 {
    //基于BST的定义来实现:针对任意节点root,其左子树中的节点值都小于root,右子树中的节点值都大于root,且左、右子树也为二叉搜索树
    //递归实现
    //时间复杂度O(N) 需要对整棵树进行一次遍历
    //空间复杂度O(N) 递归栈的空间
    public boolean isValidBST(TreeNode root) {
        //边界条件
        if (root == null) {
            return true;
        }

        //递归校验
        return isBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isBST(TreeNode root, long min, long max) {
        if (root == null) {  //递归终止条件
            return true;
        }

        if (root.val <= min || root.val >= max) {    //不满足BST定义
            return false;
        }

        //递归判断左、右子树
        return isBST(root.left, min, root.val) && isBST(root.right, root.val, max);
    }
}
