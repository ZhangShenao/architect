package william.algorithm.bst;

import william.algorithm.util.RandomArrayGenerator;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

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
     * 前序遍历
     */
    public void preOrder() {
        System.err.println("前序遍历: ");
        preOrder(root);
        System.err.println();
    }

    private void preOrder(Node<K, V> node) {
        if (node == null) {
            return;
        }
        System.err.print(node.key + " , ");
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        System.err.println("中序遍历");
        inOrder(root);
        System.err.println();
    }

    private void inOrder(Node<K, V> node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.err.print(node.key + " , ");
        inOrder(node.right);
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        System.err.println("后序遍历: ");
        postOrder(root);
        System.err.println();
    }

    private void postOrder(Node<K, V> node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.err.print(node.key + " , ");
    }

    /**
     * 广度优先遍历(层序遍历)
     */
    public void levelOrder(){
        System.err.println("层序遍历");
        //通过一个队列保存节点
        Queue<Node<K,V>> queue = new LinkedList<>();

        //首先将根节点入队
        queue.offer(root);

        //从队列中依次取节点进行遍历
        while (!queue.isEmpty()){
            Node<K, V> node = queue.poll();
            System.err.print(node.key + " , ");

            //将节点的左、右子节点入队
            Optional.ofNullable(node.left).ifPresent(queue::offer);
            Optional.ofNullable(node.right).ifPresent(queue::offer);
        }
        System.err.println();
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

    public static void main(String[] args) throws InterruptedException {
        BST<Integer, Integer> bst = new BST<>();
        int len = 20;
        int[] array = RandomArrayGenerator.generateRandomArray(len, -100, 100);
        System.err.println("arr: " + Arrays.toString(array));
        for (int i = 0; i < len; i++) {
            bst.insert(array[i], array[i]);
        }
        bst.preOrder();
        bst.inOrder();
        bst.postOrder();
        bst.levelOrder();
        Thread.sleep(Long.MAX_VALUE);
    }
}
