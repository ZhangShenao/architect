package william.leetcode.tree;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/12 上午11:22
 * <p>
 * https://leetcode.cn/problems/balanced-binary-tree/
 */
public class 平衡二叉树_110 {
    //自底向上递归
    //对于当前根节点root,首先分别计算其左、右子树的高度
    //如果左、右子树任何一个不平衡,则整棵树不平衡
    //如果左、右子树的高度差>1,则整棵树不平衡
    //否则当前根节点root的高度=左、右子树最大高度+1
    //时间复杂度O(N),每个节点只遍历一遍,无重复计算
    //空间复杂度O(N),主要取决于递归的层数
    public boolean isBalanced(TreeNode root) {
        //边界条件
        if (root == null) {
            return true;
        }

        //递归判断
        return height(root) != -1;
    }

    //递归计算以root为根的子树的高度
    //如果该子树不平衡,则返回-1
    private int height(TreeNode root) {
        //递归终止条件
        if (root == null) {
            return 0;
        }

        //分别计算左、右子树高度
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        //如果左、右子树任何一个不平衡,或左、右子树高度差>1,则整棵树不平衡
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        //当前树高度=左、右子树最大高度+1
        return (1 + Math.max(leftHeight, rightHeight));

    }
}
