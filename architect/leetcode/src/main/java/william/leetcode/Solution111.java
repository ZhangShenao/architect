package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/11 09:45
 * @Description:https://leetcode.com/problems/minimum-depth-of-binary-tree/
 */
public class Solution111 {
    public int minDepth(TreeNode root) {
        return minDepthRecursive(root);
    }

    //递归计算最短路径
    private int minDepthRecursive(TreeNode node){
        if (node == null){
            return 0;
        }
        if (node.left == null && node.right == null){
            return 1;
        }

        //分别计算左、右子树的最短路径
        int left = 1 + minDepthRecursive(node.left);
        int right = 1 + minDepthRecursive(node.right);

        //取最短路径
        int min = Math.min(left,right);

        //如果最短路径为1,则可能存在某个子树为空,需要取最大值
        if (min <= 1){
            return Math.max(left,right);
        }

        //否则直接返回最短路径
        return min;

    }
}
