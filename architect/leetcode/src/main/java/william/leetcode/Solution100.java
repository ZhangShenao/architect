package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/27 19:47
 * @Description:https://leetcode.com/problems/same-tree/
 */
public class Solution100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return isSame(p, q);
    }

    private boolean isSame(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null && q != null) {
            return false;
        }
        if (p != null && q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSame(p.left, q.left) && isSame(p.right, q.right);
    }
}
