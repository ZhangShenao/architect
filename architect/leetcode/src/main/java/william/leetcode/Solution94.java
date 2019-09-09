package william.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/20 10:27
 * @Description:https://leetcode.com/problems/binary-tree-inorder-traversal/
 */
public class Solution94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        LinkedList<Integer> nodes = new LinkedList<>();
        inorderTraversalRecursive(root, nodes);
        return nodes;
    }

    //递归中序遍历
    private void inorderTraversalRecursive(TreeNode root, List<Integer> nodes) {
        if (root == null) {
            return;
        }
        inorderTraversalRecursive(root.left, nodes);
        nodes.add(root.val);
        inorderTraversalRecursive(root.right, nodes);
    }
}
