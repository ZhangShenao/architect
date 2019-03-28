package william.algorithm.bst;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/28 10:20
 * @Description:二叉搜索树，插入、删除和查找的时间复杂度均为O(logn) 由于BST并非完全二叉树, 所以更适合采用链表实现
 */
public class BST<K extends Comparable<K>, V> {
    private Node<K, V> root;
    private int size;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    public void insert(K key, V value) {
        root = insert(root, key, value);
    }

    /**
     * 插入节点,返回插入后的BST的根节点
     */
    private Node<K, V> insert(Node<K, V> node, K key, V value) {
        //退出条件:如果当前节点为空,则直接返回新节点
        if (node == null) {
            ++size;
            return new Node<>(key, value);
        }

        //如果存在相同的Key,则仅更新Value值
        if (node.key.compareTo(key) == 0) {
            node.value = value;
            return node;
        }

        //将当前节点的Key和待插入的Key进行比较,决定向左子树插入还是向右子树插入
        if (key.compareTo(node.key) > 0) {
            node.right = insert(node.right, key, value);
        } else {
            node.left = insert(node.left, key, value);
        }
        ++size;
        return node;

    }

    public boolean contains(K key) {
        return contains(root, key);
    }

    public V search(K key) {
        return search(root, key);
    }

    /**
     * 从node节点开始递归查找是否存在指定Key的元素
     */
    private boolean contains(Node<K, V> node, K key) {
        if (node == null) {
            return false;
        }
        if (node.key.compareTo(key) == 0) {
            return true;
        }
        if (key.compareTo(node.key) > 0) {
            return contains(node.right, key);
        }
        return contains(node.left, key);
    }

    /**
     * 从node节点开始查找指定Key的元素,如果不存在则返回null
     *
     * @param node
     * @param key
     * @return
     */
    private V search(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            return node.value;
        }
        if (key.compareTo(node.key) > 0) {
            return search(node.right, key);
        }
        return search(node.left, key);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * BST内部节点
     */
    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = this.right = null;
        }
    }

    public static void main(String[] args) {
        BST<Integer, Integer> bst = new BST<>();
        bst.insert(10,10);
        bst.insert(100,100);
        bst.insert(-99,-99);
        bst.insert(87,87);
        bst.insert(999,999);
        bst.insert(-1,-1);
        System.err.println(bst.contains(10));
        System.err.println(bst.contains(10000));
        System.err.println(bst.search(-1));
        System.err.println(bst.search(-5));
    }
}
