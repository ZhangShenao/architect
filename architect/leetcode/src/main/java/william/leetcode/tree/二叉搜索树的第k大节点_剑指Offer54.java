package william.leetcode.tree;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/27 下午1:33
 * <p>
 * https://leetcode.cn/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/
 */
public class 二叉搜索树的第k大节点_剑指Offer54 {
    private int result;    //保存最终结果
    private int k;  //记录k值

    //使用中序遍历+提前终止
    //按照右-根-左的顺序中序遍历二叉树
    //每遍历到一个节点,执行k--,记录当前遍历到的元素数量
    //当k=0时,说明已经找到第k大的元素,提前终止递归
    public int kthLargest(TreeNode root, int k) {
        //边界条件
        if (root == null || k < 1) {
            return -1;
        }

        this.k = k;
        //对BST进行倒序中序遍历
        reverseInorder(root);

        return result;
    }

    //对以root为根的BST进行倒序中序遍历
    private void reverseInorder(TreeNode root) {
        if (root == null) {  //递归终止条件
            return;
        }

        if (k == 0) {    //如果k==0,说明已经找到第k大的元素,可以提前终止递归
            return;
        }

        //按照右-中-左的顺序遍历
        reverseInorder(root.right);

        //每访问一个节点,进行--k
        if (--k == 0) {
            result = root.val;
        }

        reverseInorder(root.left);
    }
}
