package william.leetcode;

import java.util.*;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/20 12:12
 * @Description:https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 */
public class Solution103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int depth = 0;
        while (!q.isEmpty()) {
            int level = q.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < level; i++) {
                root = q.poll();
                if (root.left != null) {
                    q.offer(root.left);
                }
                if (root.right != null) {
                    q.offer(root.right);
                }
                if (depth % 2 != 0) {
                    temp.add(0, root.val);
                } else {
                    temp.add(root.val);
                }
            }
            ++depth;
            res.add(temp);
        }

        return res;
    }
}
