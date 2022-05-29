package william.leetcode.tree;


/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/29 上午11:52
 * <p>
 * https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/
 */
public class 二叉树展开为链表_114 {
    //采用遍历+修改指针的方式
    //找到根节点左子树中的最右节点
    //根节点的将右子树挂在最右节点的右子节点上
    //然后将根节点的左子树变为右子树
    //时间复杂度O(N) 需遍历一次树
    //空间复杂度(1)
    public void flatten(TreeNode root) {
        //边界条件
        if (root == null) {
            return;
        }

        TreeNode cur = root;

        //遍历树
        while (cur != null) {
            if (cur.left != null) {
                //找到左子树中的最右节点
                TreeNode rightMost = cur.left;
                while (rightMost.right != null) {
                    rightMost = rightMost.right;
                }

                //将根节点的右子树挂在最右节点的右子树下
                rightMost.right = cur.right;

                //将根节点的右子树变为左子树
                cur.right = cur.left;

                //将根节点的左子树置为空
                cur.left = null;
            }

            //遍历下一个根节点
            cur = cur.right;
        }
    }

    //使用前序遍历实现
    //对树进行前序遍历,使用List保存遍历结果
    //遍历完成后,再遍历List,构造链表
    //时间复杂度O(N) 需要对树和List都进行一次遍历
    //空间复杂度O(N) 需要额外使用List空间
//    public void flatten(TreeNode root) {
//        //边界条件
//        if (root == null) {
//            return;
//        }
//
//        List<TreeNode> list = new LinkedList<>();
//        preorder(root, list);
//
//        //遍历List,构造链表
//        for (int i = 1; i < list.size(); i++) {
//            TreeNode prev = list.get(i - 1);
//            TreeNode cur = list.get(i);
//            prev.right = cur;
//            prev.left = null;
//        }
//    }

    //对树递归进行前序遍历
//    private void preorder(TreeNode root, List<TreeNode> list) {
//        if (root == null) {  //递归终止条件
//            return;
//        }
//        list.add(root);
//        preorder(root.left, list);
//        preorder(root.right, list);
//    }
}
