package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/20 13:26
 * @Description:https://leetcode.com/problems/search-in-a-binary-search-tree/
 */
public class Solution700 {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null){
            return null;
        }
        if (root.val == val){
            return root;
        }
        if (root.val > val){
            return searchBST(root.left,val);
        }
        return searchBST(root.right,val);
    }
}
