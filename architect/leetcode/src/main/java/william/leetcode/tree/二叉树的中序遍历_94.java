package william.leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/10 上午9:49
 * <p>
 * https://leetcode.cn/problems/binary-tree-inorder-traversal/
 */
public class 二叉树的中序遍历_94 {
    //使用迭代方式中序遍历二叉树
    //借助栈实现
    //时间复杂度O(n)
    //空间复杂度O(n)
    public List<Integer> inorderTraversal(TreeNode root) {
        //边界条件
        if (root == null) {
            return Collections.emptyList();
        }

        List<Integer> result = new ArrayList<>();   //保存最终结果

        Stack<TreeNode> stack = new Stack<>();  //创建一个栈,暂存节点

        //迭代
        while (root != null || !stack.isEmpty()) {
            //dfs左子树,将路径上的节点入栈
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            //左子树dfs结束,节点出栈
            TreeNode node = stack.pop();
            result.add(node.val);

            //访问右子树
            root = node.right;
        }

        return result;
    }

    //递归中序遍历二叉树
    //时间复杂度O(n)
    //空间复杂度O(n)
    private void inorderTraversalRecursively(List<Integer> result, TreeNode root) {
        //递归终止条件
        if (root == null) {
            return;
        }

        //遍历左子树
        inorderTraversalRecursively(result, root.left);

        //访问根节点
        result.add(root.val);

        //遍历右子树
        inorderTraversalRecursively(result, root.right);
    }
}
