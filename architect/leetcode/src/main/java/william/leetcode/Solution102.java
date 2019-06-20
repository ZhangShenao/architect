package william.leetcode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/20 10:38
 * @Description:https://leetcode.com/problems/binary-tree-level-order-traversal/
 */
public class Solution102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        List<List<Integer>> result = new LinkedList<>();

        //借助一个队列
        Queue<TreeNode> queue = new LinkedList<>();

        //记录当前层数
        int level = -1;

        //记录当前层的节点数
        int size = 0;

        //首先将根节点入队
        queue.offer(root);
        while (!queue.isEmpty()) {
            //记录当前层数和节点数
            size = queue.size();
            ++level;

            //保存当前层的节点
            LinkedList<Integer> nodes = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                //遍历当前节点
                TreeNode node = queue.poll();
                if (node == null) {
                    break;
                }
                nodes.add(node.val);

                //依次将该节点的左、右子节点入队
                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            result.add(level, nodes);
        }

        return result;
    }
}
