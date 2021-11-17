package william.algo.basic.tree;

/**
 * @Author zhangshenao
 * @Date 2021-11-17
 * @Description 二叉搜索树
 */
public class BinarySearchTree {
    private Node root;

    //构造一个空树
    public BinarySearchTree() {
        this.root = null;
    }

    //查找
    public Node find(int val) {
        Node n = root;
        while (n != null) {
            if (n.value == val) {
                return n;
            }
            if (n.value > val) {
                n = n.right;
            } else {
                n = n.left;
            }
        }
        return null;
    }

    //插入
    public void insert(int val) {
        if (root == null) { //空树插入
            root = new Node(val);
            return;
        }

        Node n = root;
        while (n != null) {
            if (n.value == val) {    //重复元素,直接丢弃
                return;
            }
            if (n.value > val) { //在左子树插入
                if (n.left == null) {
                    n.left = new Node(val);
                    return;
                }
                n = n.left;
            } else { //在右子树插入
                if (n.right == null) {
                    n.right = new Node(val);
                } else {
                    n = n.right;
                }
            }
        }
    }

    //找到最小节点
    public Node findMin() {
        if (root == null) {
            return null;
        }
        Node n = root;
        while (n.left != null) {
            n = n.left;
        }

        return n;
    }

    //找到最大节点
    public Node findMax() {
        if (root == null) {
            return null;
        }

        Node n = root;
        while (n.right != null) {
            n = n.right;
        }

        return n;
    }


    //BST的节点定义
    private static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
}
