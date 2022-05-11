package william.leetcode.tree;


/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/11 上午7:49
 * <p>
 * https://leetcode.cn/problems/symmetric-tree/
 */
public class 对称二叉树_101 {
    //递归实现
    //判断是否为对称二叉树:左子树的左子节点=右子树的右子节点 && 左子树的右子节点=右子树的左子节点
    //时间复杂度O(N)
    //空间复杂度O(1)
    public boolean isSymmetric(TreeNode root) {
        //边界条件
        if (root == null) {
            return false;
        }

        return isSymmetricRecursively(root.left, root.right);
    }

    //迭代实现
    //借助队列,对二叉树进行层序遍历,依次入队根节点左子树的左子节点->右子树的右子节点->左子树的右子节点->右子树的左子节点
    //每次出队两个节点,判断是否相等
    //时间复杂度O(N)
    //空间复杂度O(1)
//    public boolean isSymmetric(TreeNode root) {
//        //边界条件
//        if (root == null) {
//            return false;
//        }
//
//        //借助队列
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.offer(root.left);
//        queue.offer(root.right);
//
//        //遍历队列
//        while (!queue.isEmpty()) {
//            //依次出队两个节点
//            TreeNode p = queue.poll();
//            TreeNode q = queue.poll();
//
//            //判断两个节点是否相等
//            if (p == null && q == null) {   //满足条件
//                continue;
//            }
//
//            if (p == null || q == null) {    //不队称
//                return false;
//            }
//
//            if (p.val != q.val) {    //节点不相等,不队称
//                return false;
//            }
//            //依次入队根节点左子树的左子节点->右子树的右子节点->左子树的右子节点->右子树的左子节点
//            queue.offer(p.left);
//            queue.offer(q.right);
//            queue.offer(p.right);
//            queue.offer(q.left);
//        }
//
//        //队列遍历完成,说明对称
//        return true;
//    }

    private boolean isSymmetricRecursively(TreeNode left, TreeNode right) {
        //递归终止条件
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }

        //递归判断:左子树的左子节点=右子树的右子节点 && 左子树的右子节点=右子树的左子节点
        return isSymmetricRecursively(left.left, right.right) && isSymmetricRecursively(left.right, right.left);
    }
}
