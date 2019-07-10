package william.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/10 11:50
 * @Description:https://leetcode.com/problems/maximum-depth-of-binary-tree/
 */
public class Solution104 {
    public int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        //基于层次遍历的思想
        int depth = 0;

        //借助一个队列保存元素
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            ++depth;
            for (int i = 0;i < size;i++){
                TreeNode node = queue.poll();
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
        }
        return depth;
    }

    //递归查找树的最大高度
    private int maxDepthRecursively(TreeNode root){
        if (root == null){
            return 0;
        }
        return 1 + Math.max(maxDepthRecursively(root.left),maxDepthRecursively(root.right));
    }
}
