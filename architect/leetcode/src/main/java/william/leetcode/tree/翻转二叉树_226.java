package william.leetcode.tree;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/12 上午11:33
 * <p>
 * https://leetcode.cn/problems/invert-binary-tree/
 */
public class 翻转二叉树_226 {
    //递归实现
    //针对以root为根的二叉树,首先依次翻转其左、右子树,然后再交换左、右子节点,最后返回root根节点
    //时间复杂度O(N)
    //空间复杂度O(N),主要取决于递归深度
    public TreeNode invertTree(TreeNode root) {
        //递归终止条件
        if (root == null) {
            return root;
        }

        //首先依次翻转左、右子树
        invertTree(root.left);
        invertTree(root.right);

        //然后交换左、右子节点
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        //最后返回root根节点
        return root;
    }
}
