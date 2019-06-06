package william.ds.bst;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/6 13:40
 * @Description:二叉搜索树ADT
 */
public class BinarySearchTree<K extends Comparable<K>, V> implements SymbolTable<K, V> {
    //根节点
    private Node root;

    /**
     * 中序遍历
     * 二叉搜索树的中序遍历返回升序排列的数组
     */
    public void inOrder() {
        inOrder(root);
        System.err.println();
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.err.print(" " + node.key + " ");
        inOrder(node.right);
    }

    @Override
    public void insert(K key, V value) {
        root = insert(root, key, value);
    }

    /**
     * 向以node为根的树插入<key,value>节点,并返回插入后的根节点
     */
    private Node insert(Node node, K key, V value) {
        //如果node为空,则创建新的节点并直接返回
        if (node == null) {
            return new Node(key, value);
        }

        //将node的key与指定key进行比较
        int compare = node.key.compareTo(key);

        //如果key相同,则直接用新的value覆盖旧的
        if (compare == 0) {
            node.value = value;
        } else if (compare > 0) {
            //如果node的key较大,则尝试向node的左子树中插入
            node.left = insert(node.left, key, value);
        } else {
            //如果node的key较小,则尝试向node的右子树中插入
            node.right = insert(node.right, key, value);
        }

        //返回当前根节点
        return node;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    /**
     * 在以node为根的树中查找指定key的节点,如果不存在则返回null
     */
    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }

        //根据node的key和指定key进行比较,觉得去左/右子树继续查找
        int compare = node.key.compareTo(key);
        if (compare == 0) {
            return node;
        }
        if (compare < 0) {
            return getNode(node.right, key);
        }
        return getNode(node.left, key);
    }

    @Override
    public void remove(K key) {
        root = removeNode(root,key);
    }

    /**
     * 删除以node为根的树中指定key的节点,并返回删除后的根节点
     */
    private Node removeNode(Node node, K key) {
        if (node == null) {
            return null;
        }

        //根据node的key和指定key的大小,查找指定节点
        int compare = node.key.compareTo(key);
        if (compare > 0) {
            node.left = removeNode(node.left, key);
            return node;
        }
        if (compare < 0) {
            node.right = removeNode(node.right, key);
            return node;
        }

        //node为要删除的节点
        //如果node的左子树为空,则直接返回node的右子节点
        if (node.left == null) {
            return node.right;
        }

        //如果node的右子树为空,则直接返回node的左子节点
        if (node.right == null) {
            return node.left;
        }

        //node的左、右子树都存在,则将node的后继节点(node右子树中的最小节点)设为新的根节点
        //1.找到后继节点
        Node successor = minNode(node.right).copy();

        //2.在右子树中将最小的节点(后继节点)删除
        successor.right = removeMinNode(node.right);

        //3.后继节点的左子树为原来根节点的左子树
        successor.left = node.left;

        return successor;
    }

    @Override
    public boolean contains(K key) {
        return (get(key) != null);
    }

    @Override
    public boolean isEmpty() {
        return (root == null);
    }

    @Override
    public K min() {
        Node min = minNode(root);
        return (min == null ? null : min.key);
    }

    /**
     * 找到以node为根的子树中key最小的节点
     */
    private Node minNode(Node node) {
        if (node == null) {
            return null;
        }
        //如果左子树为空,则当前节点就是最小的
        if (node.left == null) {
            return node;
        }
        //否则去左子树中继续查找
        return minNode(node.left);
    }

    @Override
    public K max() {
        Node max = maxNode(root);
        return (max == null ? null : max.key);
    }

    /**
     * 找到以node为根的子树中key最大的节点
     */
    private Node maxNode(Node node) {
        if (node == null) {
            return null;
        }
        //如果右子树为空,则当前节点就是最大的
        if (node.right == null) {
            return node;
        }
        //否则去右子树中继续查找
        return maxNode(node.right);
    }


    @Override
    public void removeMin() {
        root = removeMinNode(root);
    }

    /**
     * 删除以node为根的树中key最小的节点,并返回删除后的树的根节点
     */
    private Node removeMinNode(Node node) {
        if (node == null) {
            return node;
        }

        //如果node的左子树不存在,说明node就是最小的,则直接删除node,返回node的右子节点
        if (node.left == null) {
            return node.right;
        }

        //如果node存在左子树,则从左子树中进行删除
        node.left = removeMinNode(node.left);

        //返回根节点
        return node;
    }

    @Override
    public void removeMax() {
        root = removeMaxNode(root);
    }

    /**
     * 删除以node为根的树中key最大的节点,并返回删除后的树的根节点
     */
    private Node removeMaxNode(Node node) {
        if (node == null) {
            return node;
        }

        //如果node的右子树不存在,说明node就是最大的,则直接删除node,返回node的左子节点
        if (node.right == null) {
            return node.left;
        }

        //如果node存在右子树,则从右子树中进行删除
        node.right = removeMinNode(node.right);

        //返回根节点
        return node;
    }


    //二叉搜索树的节点
    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node copy() {
            Node copy = new Node(this.key, this.value);
            copy.left = this.left;
            copy.right = this.right;
            return copy;
        }
    }
}
