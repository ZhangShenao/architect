package william.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/20 10:31
 * @Description:https://leetcode.com/problems/binary-tree-postorder-traversal/
 */
public class Solution145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> nodes = new LinkedList<>();
        postorderTraversalRecursive(root, nodes);
        return nodes;
    }

    //递归后序遍历
    private void postorderTraversalRecursive(TreeNode root, List<Integer> nodes) {
        if (root == null) {
            return;
        }
        postorderTraversalRecursive(root.left, nodes);
        postorderTraversalRecursive(root.right, nodes);
        nodes.add(root.val);
    }
}
