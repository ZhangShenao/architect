package william.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/11 上午8:07
 * <p>
 * https://leetcode.cn/problems/maximum-depth-of-binary-tree/
 */
public class 二叉树的最大深度_104 {
    //递归实现
    //二叉树的最大深度=1+左、右子树中的最大深度
    //时间复杂度O(N)
    //空间复杂度O(1)
    public int maxDepth(TreeNode root) {
        //递归终止条件
        if (root == null) {
            return 0;
        }

        //递推公式:二叉树的最大深度=1+左、右子树中的最大深度
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    //迭代实现
    //借助队列,进行层序遍历。记录层数,每次遍历到一下层,对层数+1
    //时间复杂度O(N)
    //空间复杂度O(N)
//    public int maxDepth(TreeNode root) {
//        //边界条件
//        if (root == null) {
//            return 0;
//        }
//
//        Queue<TreeNode> queue = new LinkedList<>(); //借助队列
//        int level = 0;  //记录层数
//        queue.offer(root);
//
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//            if (size <= 0) {
//                break;
//            }
//            ++level;    //遍历到下一层,层数+1
//            //遍历当前层,并将下一层子节点入队
//            for (int i = 0; i < size; i++) {
//                TreeNode n = queue.poll();
//                if (n.left != null) {
//                    queue.offer(n.left);
//                }
//                if (n.right != null) {
//                    queue.offer(n.right);
//                }
//            }
//        }
//
//        return level;
//    }
}
