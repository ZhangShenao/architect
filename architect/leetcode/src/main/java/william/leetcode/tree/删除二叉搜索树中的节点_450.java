package william.leetcode.tree;

/**
 * https://leetcode-cn.com/problems/delete-node-in-a-bst/description/
 */
public class 删除二叉搜索树中的节点_450 {
    //使用递归实现
    //首先基于二叉搜索树的性质,找到需要删除的节点root
    //如果root的左子树为空,则直接用右子树替换左子树
    //如果root的右子树为空,则直接用左子树替换右子树
    //如果root的左、右子树均不为空,则找到右子树中的最左(最小)节点,将左子树挂在最左节点的左侧,然后返回右子树根节点
    //时间复杂度O(N) 需要遍历一次二叉搜索树
    //空间复杂度O(N) 递归栈空间
    public TreeNode deleteNode(TreeNode root, int key) {
        //递归终止条件
        if (root == null) {
            return null;
        }

        //利用二叉搜索树的性质,定位到要删除的节点
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else {
            if (root.left == null) {  //如果root的左子树为空,则直接用右子树替换左子树
                return root.right;
            }
            if (root.right == null) {    //如果root的右子树为空,则直接用左子树替换右子树
                return root.left;
            }

            //root的左、右子树均不为空,则找到右子树中的最左(最小)节点,将左子树挂在最左节点的左侧,然后返回右子树根节点
            TreeNode rightMin = root.right;
            while (rightMin.left != null) {
                rightMin = rightMin.left;
            }
            rightMin.left = root.left;
            root = root.right;
        }

        //返回删除后的根节点
        return root;
    }
}
