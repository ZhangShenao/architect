package william.interview.algo.tree;

public class 二叉树的最近公共祖先236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //递归终止条件
        if (root == null) {
            return null;
        }

        //如果当前节点是p或者q,则直接返回
        if (root == p || root == q) {
            return root;
        }

        //分别在左、右子树中查找节点q或节点q
        TreeNode l = lowestCommonAncestor(root.left, p, q);
        TreeNode r = lowestCommonAncestor(root.right, p, q);

        //如果在左、右子树中都找到了,则说明root即为最小公共祖先
        if (l != null && r != null) {
            return root;
        }

        //否则l或r就是p和q的最小公共祖先
        return (l != null) ? l : r;
    }

}
