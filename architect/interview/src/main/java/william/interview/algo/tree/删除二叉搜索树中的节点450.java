package william.interview.algo.tree;

/**
 * https://leetcode-cn.com/problems/delete-node-in-a-bst/description/
 */
public class 删除二叉搜索树中的节点450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        //边界条件
        if (root == null) {
            return root;
        }

        //找到值为key的节点
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else { //找到指定节点
            //当前节点为叶子节点,直接置为null即可
            if (root.left == null && root.right == null) {
                return null;
            }

            //如果有左子树,则找到左子树中的最大值,先交换后删除
            else if (root.left != null) {
                TreeNode max = root.left;
                while (max.right != null) {
                    max = max.right;
                }

                //交换
                swapNodeValue(root, max);

                //在左子树中删除节点
                root.left = deleteNode(root.left, key);
            }

            //如果有右子树,则找到右子树中的最小值,先交换后删除
            else if (root.right != null) {
                TreeNode min = root.right;
                while (min.left != null) {
                    min = min.left;
                }

                //交换
                swapNodeValue(root, min);

                //在右子树中删除节点
                root.right = deleteNode(root.right, key);
            }
        }


        //返回删除后的根节点
        return root;
    }


    private void swapNodeValue(TreeNode n1, TreeNode n2) {
        int tmp = n1.val;
        n1.val = n2.val;
        n2.val = tmp;
    }

}
