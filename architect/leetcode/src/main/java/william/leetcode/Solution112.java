package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/11 09:58
 * @Description:https://leetcode.com/problems/path-sum/
 */
public class Solution112 {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        //返回true的条件:当前节点为叶子节点 && 当前节点的值=剩余的值
        if (root.left == null && root.right == null && root.val == sum) {
            return true;
        }

        //如果当前节点不满足,则依次向左、右子树查找
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
