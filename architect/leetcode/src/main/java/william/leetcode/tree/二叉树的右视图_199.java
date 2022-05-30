package william.leetcode.tree;

import java.util.*;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/30 上午8:38
 * https://leetcode.cn/problems/binary-tree-right-side-view/
 */
public class 二叉树的右视图_199 {
    //采用逆序层序遍历的方式
    //针对每层,首先遍历右子节点,然后遍历左子节点,最后在结果集中保存当前层的第一个元素
    //时间复杂度O(N) 需要对树进行一次遍历
    //空间复杂度O(N) 需要额外借助队列的空间
//    public List<Integer> rightSideView(TreeNode root) {
//        //边界条件
//        if (root == null) {
//            return Collections.emptyList();
//        }
//
//        Queue<TreeNode> queue = new ArrayDeque<>(); //借助队列
//
//        List<Integer> result = new LinkedList<>();  //保存结果
//
//        queue.offer(root);  //首先将根节点入队
//
//        //遍历二叉树
//        while (!queue.isEmpty()) {
//            //获取当前层节点数量
//            int curLevelCount = queue.size();
//
//            //遍历当前层
//            for (int i = 0; i < curLevelCount; i++) {
//                //先访问右子节点,再访问左子节点
//                TreeNode cur = queue.poll();
//                if (cur.right != null) {
//                    queue.offer(cur.right);
//                }
//                if (cur.left != null) {
//                    queue.offer(cur.left);
//                }
//
//                //保存当前层第一个节点
//                if (i == 0) {
//                    result.add(cur.val);
//                }
//            }
//        }
//
//        //返回结果
//        return result;
//    }

    //采用递归+记录层数的方式实现
    //按照递归的方式对整颗树进行遍历,先访问右子节点,再访问左子节点,并记录当前层数
    //只有当当前层数=结果集数量时,才将当前节点保存到结果集中
    //时间复杂度O(N) 需要对整棵树进行一次遍历
    //空间复杂度O(N) 递归栈所需的空间
    public List<Integer> rightSideView(TreeNode root) {
        //边界条件
        if (root == null) {
            return Collections.emptyList();
        }

        List<Integer> result = new LinkedList<>();

        //递归调用,保存右视图节点
        addRightSideNode(root, result, 0);

        //返回结果
        return result;
    }

    private void addRightSideNode(TreeNode root, List<Integer> result, int level) {
        if (root == null) {  //递归终止条件
            return;
        }

        //仅有当结果集数量=当前层数时,才将当前节点保存至结果集中
        if (result.size() == level) {
            result.add(root.val);
        }

        //按照先右后左的顺序递归
        addRightSideNode(root.right, result, level + 1);
        addRightSideNode(root.left, result, level + 1);
    }
}
