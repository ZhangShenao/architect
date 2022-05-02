package william.leetcode.queue;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 */
public class 二叉树的层序遍历102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        //边界条件
        if (root == null) {
            return Collections.emptyList();
        }

        List<List<Integer>> result = new ArrayList<>();

        //使用队列保存树每层的节点
        Queue<TreeNode> queue = new LinkedList<>();

        //首先将根节点入队
        queue.add(root);

        //遍历队列
        while (!queue.isEmpty()) {
            //记录当前层节点的数量
            int size = queue.size();

            //创建新的list保存当前层元素
            List<Integer> curLevel = new ArrayList<>(size);
            result.add(curLevel);

            //遍历当前层
            for (int i = 0; i < size; i++) {
                //保存当前节点
                TreeNode node = queue.poll();
                curLevel.add(node.val);

                //依次将左、右子节点入队
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
        }

        return result;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
