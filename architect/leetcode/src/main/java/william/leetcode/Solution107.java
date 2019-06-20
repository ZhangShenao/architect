package william.leetcode;

import java.util.*;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/20 10:50
 * @Description:https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
 */
public class Solution107 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        //使用栈,自底向上保存每层元素
        Stack<List<Integer>> stack = new Stack<>();

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

            stack.push(nodes);
        }

        List<List<Integer>> result = new LinkedList<>();
        while (!stack.isEmpty()){
            result.add(stack.pop());
        }
        return result;
    }
}
