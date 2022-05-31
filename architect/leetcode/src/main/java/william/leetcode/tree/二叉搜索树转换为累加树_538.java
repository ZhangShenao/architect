package william.leetcode.tree;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/31 上午11:54
 * <p>
 * https://leetcode.cn/problems/convert-bst-to-greater-tree/
 */
public class 二叉搜索树转换为累加树_538 {
    private int sum;    //暂存当前和

    //采用递归+逆中序遍历的方式
    //首先累加右子树,保存右子树的和
    //然后更新当前节点的值
    //最后累加左子树
    //时间复杂度O(N) 需要对二叉搜索树进行一次遍历
    //空间复杂度O(N) 递归栈所需要的空间
    public TreeNode convertBST(TreeNode root) {
        //边界条件
        if (root == null) {
            return null;
        }

        accumulate(root);

        return root;
    }

    //采用逆中序遍历的方式,递归累加二叉搜索树
    private void accumulate(TreeNode root) {
        if (root == null) {  //递归终止条件
            return;
        }

        //累加右子树
        accumulate(root.right);

        //更新当前节点值
        root.val += sum;

        //更新累加和
        sum = root.val;

        //累加左子树
        accumulate(root.left);
    }
}
