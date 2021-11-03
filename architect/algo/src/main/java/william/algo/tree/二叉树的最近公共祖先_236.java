package william.algo.tree;

/**
 * @Author zhangshenao
 * @Date 2021-11-03
 * @Description https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * 时间复杂度O(n)
 */
public class 二叉树的最近公共祖先_236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return findPOrQ(root, p, q);
    }

    //借助一个辅助函数findPOrQ
    private TreeNode findPOrQ(TreeNode root, TreeNode p, TreeNode q) {
        //如果当前节点是,则直接返回
        if (root == null || root == p || root == q) {
            return root;
        }

        //如果在左右子树都找到了p或者q,则当前根节点就是最近的公共组件
        TreeNode left = findPOrQ(root.left, p, q);
        TreeNode right = findPOrQ(root.right, p, q);


        //否则分别去左、右子树找
        if (left != null && right != null) {
            return root;
        }

        if (left != null) {
            return left;
        }

        return right;
    }
}
