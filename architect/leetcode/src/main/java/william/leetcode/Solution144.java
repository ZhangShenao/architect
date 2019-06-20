package william.leetcode;

import java.util.*;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/20 10:11
 * @Description:https://leetcode.com/problems/binary-tree-preorder-traversal/
 */
public class Solution144 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> nodes = new LinkedList<>();
        preorderTraversalRecursive(root, nodes);
        return nodes;
    }

    //递归先序遍历
    private void preorderTraversalRecursive(TreeNode root, List<Integer> nodes) {
        if (root == null) {
            return;
        }
        nodes.add(root.val);
        preorderTraversalRecursive(root.left, nodes);
        preorderTraversalRecursive(root.right, nodes);
    }

    //迭代先序遍历
    private List<Integer> preorderTraversalIteratively(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        //借助一个栈
        Stack<TreeNode> stack = new Stack<>();

        List<Integer> nodes = new LinkedList<>();

        //首先将根节点入栈
        stack.push(root);

        //对栈进行遍历
        while (!stack.isEmpty()){
            //遍历栈顶元素
            TreeNode node = stack.pop();
            nodes.add(node.val);

            //依次将该节点的右、左子节点入栈
            if (node.right != null){
                stack.push(node.right);
            }
            if (node.left != null){
                stack.push(node.left);
            }
        }

        return nodes;
    }
}
