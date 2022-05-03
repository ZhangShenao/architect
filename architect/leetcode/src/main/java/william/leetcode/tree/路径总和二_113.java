package william.leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/path-sum-ii/description/
 */
public class 路径总和二_113 {
    //全局保存result
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        //边界条件
        if (root == null) {
            return Collections.emptyList();
        }

        List<Integer> path = new ArrayList<>();

        //递归
        findPath(root, path, 0, targetSum);

        return result;
    }

    private void findPath(TreeNode root, List<Integer> path, int cur, int targetSum) {
        //递归终止条件
        if (root == null) {
            return;
        }

        //更新当前路径和当前值
        path.add(root.val);
        cur += root.val;

        //判断是否遍历到了叶子节点
        if (root.left == null && root.right == null) {
            if (cur == targetSum) { //保存路径
                result.add(new ArrayList<>(path));  //需要copy一份path
            }
        } else {
            //依次遍历左、右子树
            findPath(root.left, path, cur, targetSum);
            findPath(root.right, path, cur, targetSum);
        }

        // 函数结束的时候弹栈，也要把结点从路径最后扔掉!
        path.remove(path.size() - 1);

    }
}
