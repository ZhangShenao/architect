package william.algo.tree;

/**
 * @Author zhangshenao
 * @Date 2021-11-03
 * @Description https://leetcode-cn.com/problems/validate-binary-search-tree/
 * 方法一：对二叉树进行中序遍历,判断遍历后的数组是否有序 时间复杂度O(n)
 * 方法二：基于二叉搜索树的定义，当前节点左子树的最大值小于该节点，右子树的最小值大于该节点，递归判断
 */
public class 验证二叉搜索树_98 {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, long min, long max) {
        //边界——空树也是二叉树
        if (root == null) {
            return true;
        }

        //根据二叉树递归定义:当前根节点大于左子树的最大值,小于右子树的最小值
        if (root.val <= min || root.val >= max) {
            return false;
        }

        //递归判断左右子树
        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }
}
