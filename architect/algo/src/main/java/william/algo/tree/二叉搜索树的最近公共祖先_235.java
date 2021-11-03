package william.algo.tree;

/**
 * @Author zhangshenao
 * @Date 2021-11-03
 * @Description 根据二叉树的特性, 在左子树或右子树遍历查找 时间复杂度O(logn)
 */
public class 二叉搜索树的最近公共祖先_235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //边界
        if (root == null || p == null || q == null) {
            return null;
        }

        if (root.val >= p.val && root.val <= q.val) {
            return root;
        }

        if (root.val >= q.val && root.val <= p.val) {
            return root;
        }

        if (root.val > q.val) {
            if (root.left == null) {
                return root;
            }
            return lowestCommonAncestor(root.left, p, q);
        }

        if (root.right == null) {
            return root;
        }

        return lowestCommonAncestor(root.right, p, q);
    }
}
