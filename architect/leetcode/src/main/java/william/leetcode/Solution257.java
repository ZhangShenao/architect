package william.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/16 10:21
 * @Description:https://leetcode.com/problems/binary-tree-paths/
 */
public class Solution257 {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> path = new ArrayList<>();
        binaryTreePathsRecursive(root, path, "");
        return path;

    }

    /**
     * 递归列出二叉树所有路径
     *
     * @param node 当前节点
     * @param path 路径集合
     * @param str  当前路径
     */
    private void binaryTreePathsRecursive(TreeNode node, List<String> path, String str) {
        if (node == null) {
            return;
        }

        //追加->
        if (!"".equals(str)) {
            str += "->";
        }

        //追加当前节点
        str += node.val;

        //如果当前节点为叶子节点,则当前路径即为一条从根到叶子的路径
        if (node.left == null && node.right == null) {
            path.add(str);
            return;
        }

        //依次处理左、右子树
        binaryTreePathsRecursive(node.left, path, str);
        binaryTreePathsRecursive(node.right, path, str);


    }


}
