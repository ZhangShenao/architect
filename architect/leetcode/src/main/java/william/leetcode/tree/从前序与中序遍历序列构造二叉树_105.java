package william.leetcode.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/29 上午11:14
 * <p>
 * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class 从前序与中序遍历序列构造二叉树_105 {
    private Map<Integer, Integer> inOrderMap = new HashMap<>();   //使用map保存中序数组中的所有元素,提升查询效率

    //采用递归实现
    //首先在前序遍历中取根节点(第一个节点)
    //然后在中序遍历中找到根节点的位置,构造左子树和右子树
    //使用map保存中序数组中的所有元素,提升查询效率
    //时间复杂度O(N) 需要对两个数组进行遍历
    //空间复杂度O(N) 递归栈的空间复杂度
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //边界条件
        if (preorder == null || inorder == null) {
            return null;
        }

        for (int i = 0; i < inorder.length; i++) {
            inOrderMap.put(inorder[i], i);
        }


        return buildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd) {
        //递归终止条件
        if (preStart > preEnd) {
            return null;
        }

        //前序数组的首个元素为根节点
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);

        //在中序数组中找到根节点的索引
        int rootIdx = inOrderMap.get(rootVal);

        //获取左子树中节点数目
        int leftCount = rootIdx - inStart;

        //构造左子树
        //preorder中左子树的范围[preStart+1,preStart+leftCount]
        //inorder中左子树的范围[inStart,rootIdx-1]
        root.left = buildTree(preorder, inorder, preStart + 1, preStart + leftCount, inStart, rootIdx - 1);

        //构造右子树
        //preorder中右子树的范围[preStart+1+leftCount,preEnd]
        //inorder中右子树的范围[rootIdx+1,rootEnd]
        root.right = buildTree(preorder, inorder, preStart + 1 + leftCount, preEnd, rootIdx + 1, inEnd);

        return root;
    }
}
