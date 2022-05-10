package william.leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/10 上午10:04
 * <p>
 * https://leetcode.cn/problems/binary-tree-postorder-traversal/
 */
public class 二叉树的后序遍历_145 {
    //采用递归实现
    //时间复杂度O(n)
    //空间复杂度O(n)
    public List<Integer> postorderTraversal(TreeNode root) {
        //边界条件
        if (root == null) {
            return Collections.emptyList();
        }

        List<Integer> result = new ArrayList<>();   //保存最终结果

        //递归
        postorderTraversalRecursively(result, root);

        return result;
    }

    private void postorderTraversalRecursively(List<Integer> result, TreeNode root) {
        //递归终止条件
        if (root == null) {
            return;
        }

        //遍历左子树
        postorderTraversalRecursively(result, root.left);

        //遍历右子树
        postorderTraversalRecursively(result, root.right);

        //访问根节点
        result.add(root.val);
    }
}
