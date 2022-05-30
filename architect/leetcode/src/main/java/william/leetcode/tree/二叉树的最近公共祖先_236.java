package william.leetcode.tree;

/**
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
 */
public class 二叉树的最近公共祖先_236 {
    //递归实现
    //在以root为根的左、右子树中分别寻找节点p和节点q,结果仅包含以下三种情况:
    //1: 在左、右子树中都返回了结果,那么当前根节点root即为最近公共祖先
    //2: 仅在左子树中返回了结果,那么说明节点p、q都在root的左子树中,则左子节点即为最近公共祖先
    //3: 尽在右子树中返回了结果,那么说明节点p、q都在root的右子树中,则右子节点即为最近公共祖先
    //时间复杂度O(N) 需要遍历一次二叉树
    //空间复杂度O(N) 递归栈空间
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //递归终止条件
        if (root == null) {  //如果遍历到了叶子节点,则返回null
            return null;
        }

        if (root == p || root == q) {   //当前根节点即为节点p或节点q,直接返回
            return root;
        }

        //在root的左、右子树中分别寻找节点p或节点q
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) { //在左、右子树中都返回了结果,那么当前根节点root即为最近公共祖先
            return root;
        }

        //结果存在于左/右子树中
        return (left == null ? right : left);
    }

}
