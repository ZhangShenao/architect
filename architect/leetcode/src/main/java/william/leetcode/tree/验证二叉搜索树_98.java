package william.leetcode.tree;

/**
 * https://leetcode-cn.com/problems/validate-binary-search-tree/description/
 */
public class 验证二叉搜索树_98 {
    //全局控制条件
    private boolean valid = true;

    public boolean isValidBST(TreeNode root) {
        //边界条件
        if (root == null) {  //空树也是二叉搜索树
            return true;
        }

        //从根节点开始递归遍历
        preOrder(root, Long.MIN_VALUE, Long.MAX_VALUE);

        return valid;
    }

    //对二叉树进行一次前序遍历,一次判断每个节点是否在指定范围内(影子二叉树)
    private void preOrder(TreeNode root, long left, long right) {
        if (!valid) {   //递归终止条件
            return;
        }
        if (root == null) {  //遍历到叶子节点
            return;
        }

        //不满足BST定义,直接终止
        if (root.val <= left || root.val >= right) {
            valid = false;
            return;
        }

        //递归判断左、右子树
        preOrder(root.left, left, root.val);
        preOrder(root.right, root.val, right);
    }
}
