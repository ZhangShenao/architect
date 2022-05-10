package william.leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/10 上午10:00
 *
 * https://leetcode.cn/problems/binary-tree-preorder-traversal/
 */
public class 二叉树的前序遍历_144 {
    //使用递归实现
    //时间复杂度O(n)
    //空间复杂度O(n)
    public List<Integer> preorderTraversal(TreeNode root) {
        //边界条件
        if (root == null) {
            return Collections.emptyList();
        }

        List<Integer> result = new ArrayList<>();   //保存最终结果

        //递归
        preorderTraversalRecursively(result, root);
        return result;
    }

    private void preorderTraversalRecursively(List<Integer> result, TreeNode root) {
        //递归终止条件
        if (root == null) {
            return;
        }

        //访问根节点
        result.add(root.val);

        //遍历左子树
        preorderTraversalRecursively(result, root.left);

        //遍历右子树
        preorderTraversalRecursively(result, root.right);
    }
}
