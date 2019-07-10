package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/10 11:08
 * @Description:https://leetcode.com/problems/symmetric-tree/
 */
public class Solution101 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return checkSymmetric(root.left, root.right);
    }

    //递归判断一个树是否对称
    private boolean checkSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null){
            return true;
        }
        if (left == null){
            return false;
        }
        if (right == null){
            return false;
        }
        if (left.val != right.val){
            return false;
        }
        //左的左=右的右 && 左的右=右的左
        return checkSymmetric(left.left,right.right) && checkSymmetric(left.right,right.left);
    }
}
