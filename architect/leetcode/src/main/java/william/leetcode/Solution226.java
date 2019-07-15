package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/15 10:48
 * @Description:https://leetcode.com/problems/invert-binary-tree/
 */
public class Solution226 {
    public TreeNode invertTree(TreeNode root) {
        return invertTreeRecursive(root);
    }

    public TreeNode invertTreeRecursive(TreeNode root) {
        if (root == null) {
            return root;
        }
        if (root.left == null && root.right == null) {
            return root;
        }
        TreeNode node = root.left;
        root.left = root.right;
        root.right = node;

        invertTreeRecursive(root.left);
        invertTreeRecursive(root.right);
        return root;
    }

}
