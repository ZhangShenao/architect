package william.leetcode.tree;

import java.util.*;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/28 上午11:35
 * <p>
 * https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/
 */
public class 二叉树的锯齿形层序遍历_103 {
    //借助队列保存每层的节点
    //并且记录当前高度,根据当前高度的奇偶性判断遍历的顺序
    //时间复杂度O(N) 需要对二叉树进行一次遍历
    //空间复杂度O(N) 需要额外使用队列的空间
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        //边界条件
        if (root == null) {
            return Collections.emptyList();
        }

        Queue<TreeNode> queue = new ArrayDeque<>(); //借助队列保存节点
        List<List<Integer>> result = new ArrayList<>(); //保存结果
        int height = 0; //记录当前高度
        queue.offer(root);

        //遍历二叉树
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean odd = (++height % 2 == 1);  //判断当前高度的奇偶性
            List<Integer> cur = new ArrayList<>();
            result.add(cur);

            for (int i = 0; i < size; i++) {
                //将子节点入队
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }

                //根据奇偶性,遍历当前节点
                if (odd) {
                    cur.add(node.val);
                } else {
                    cur.add(0, node.val);
                }
            }
        }

        return result;
    }
}
