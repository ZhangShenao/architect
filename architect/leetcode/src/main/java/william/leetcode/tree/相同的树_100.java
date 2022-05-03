package william.leetcode.tree;

/**
 * https://leetcode-cn.com/problems/same-tree/description/
 */
public class 相同的树_100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        //递归终止条件
        if (p == null && q == null) {
            return true;
        }
        if (p == null && q != null) {
            return false;
        }
        if (p != null && q == null) {
            return false;
        }

        //递归判断
        return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
