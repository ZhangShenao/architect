package william.algo.bfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import william.algo.tree.TreeNode;

/**
 * @Author zhangshenao
 * @Date 2021-11-06
 * @Description 二叉树层序遍历
 * 借助一个队列保存每层的元素
 * 时间复杂度O(n)
 * 空间复杂度O(n)
 */
public class 二叉树的层序遍历_102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        //边界条件
        if (root == null) {
            return Collections.emptyList();
        }

        //借助一个队列,利用其FIFO特性,保存子节点
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        queue.offer(root);

        //遍历二叉树
        while (!queue.isEmpty()) {
            //保存当前层的节点
            List<Integer> curLayer = new LinkedList<>();
            int curLayerNum = queue.size();
            for (int i = 0; i < curLayerNum; i++) {
                TreeNode node = queue.poll();
                curLayer.add(node.val);

                //按照顺序依次将当前节点的左、右子节点入队
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(curLayer);
        }

        return result;
    }
}
