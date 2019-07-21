package william.enhance.bst;

import william.utils.AlgorithmUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/21 09:44
 * @Description:基于链表实现的二叉搜索树
 */
public class LinkedBinarySearchTree<K extends Comparable<K>, V> implements BinarySearchTree<K, V> {
    //根节点
    private Node root;

    //记录树中节点数量
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void insert(K key, V value) {
        root = insert(root, key, value);
    }

    //向以node为根的二叉树中插入节点,并返回插入后的根节点
    private Node insert(Node node, K key, V value) {
        //如果当前节点为空,则直接插入新节点
        if (node == null) {
            ++size;
            return new Node(key, value);
        }

        //如果key相等,则直接用新value覆盖旧value
        if (node.key.compareTo(key) == 0) {
            node.value = value;
        }

        //如果key不相等,则根据key比较决定向左、右子树插入
        else if (node.key.compareTo(key) > 0) {
            node.left = insert(node.left, key, value);
        } else {
            node.right = insert(node.right, key, value);
        }

        return node;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean contains(K key) {
        return contains(root, key);
    }

    //在以node为根的数组判断key是否存在
    private boolean contains(Node node, K key) {
        if (node == null) {
            return false;
        }
        if (node.key.compareTo(key) == 0) {
            return true;
        }
        if (node.key.compareTo(key) > 0) {
            return contains(node.left, key);
        }
        return contains(node.right, key);
    }

    @Override
    public V search(K key) {
        return search(root, key);
    }

    //在以node为根的树中查找key
    private V search(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (node.key.compareTo(key) == 0) {
            return node.value;
        }

        if (node.key.compareTo(key) > 0) {
            return search(node.left, key);
        }

        return search(node.right, key);
    }

    @Override
    public void preOrder() {
        ArrayList<K> keys = new ArrayList<>();
        preOrder(root, keys);
        System.err.println("前序遍历: " + keys);
    }

    //前序遍历以node为根的树
    private void preOrder(Node node, List<K> keys) {
        if (node == null) {
            return;
        }
        keys.add(node.key);
        preOrder(node.left, keys);
        preOrder(node.right, keys);
    }

    @Override
    public void inOrder() {
        List<K> keys = new ArrayList<>();
        inOrder(root, keys);
        System.err.println("中序遍历: " + keys);
    }

    //中序遍历以node为根的树
    private void inOrder(Node node, List<K> keys) {
        if (node == null) {
            return;
        }
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    @Override
    public void postOrder() {
        List<K> keys = new ArrayList<>();
        postOrder(root, keys);
        System.err.println("后序遍历: " + keys);
    }

    //后序遍历以node为根的树
    private void postOrder(Node node, List<K> keys) {
        if (node == null) {
            return;
        }
        postOrder(node.left, keys);
        postOrder(node.right, keys);
        keys.add(node.key);
    }

    @Override
    public void levelOrder() {
        if (root == null) {
            return;
        }

        List<K> keys = new ArrayList<>();

        //借助队列
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            keys.add(node.key);

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        System.err.println("层序遍历: " + keys);
    }

    @Override
    public K min() {
        return (root == null ? null : min(root).key);
    }

    //获取以node为根的树中的最小节点
    private Node min(Node node) {
        return (node.left == null ? node : min(node.left));
    }

    @Override
    public K max() {
        return (root == null ? null : max(root).key);
    }

    //获取以node为根的树中的最大节点
    private Node max(Node node) {
        return node.right == null ? node : max(node.right);
    }

    @Override
    public void removeMin() {
        removeMin(root);
        System.err.println("删除最小节点");
    }

    //删除以node为根的树中的最小节点,并返回删除后树的根节点
    private Node removeMin(Node node) {
        if (node == null) {
            return null;
        }

        //如果node的左子树为空,说明node即为最小的节点,则直接删除node
        if (node.left == null) {
            --size;
            return node.right;
        }

        //否则递归去node的左子树中进行删除
        node.left = removeMin(node.left);
        return node;
    }

    @Override
    public void removeMax() {
        removeMax(root);
        System.err.println("删除最大节点");
    }

    //删除以node为根的树中的最大节点,并返回删除后的根节点
    private Node removeMax(Node node) {
        if (node == null) {
            return null;
        }

        //如果node的右子树为空,说明node就是最大节点,则直接删除node
        if (node.right == null) {
            --size;
            return node.left;
        }

        //否则递归去删除node的右子树
        node.right = removeMax(node.right);
        return node;
    }

    @Override
    public void remove(K key) {
        root = remove(root, key);
        System.err.println("删除key: " + key);
    }

    //删除以node为根的树中的key,返回删除后的根节点
    private Node remove(Node node, K key) {
        //找到待删除的节点
        if (node == null) {
            return null;
        }
        if (node.key.compareTo(key) > 0) {
            node.left = remove(node.left, key);
            return node;
        }
        if (node.key.compareTo(key) < 0) {
            node.right = remove(node.right, key);
            return node;
        }


        //node为待删除的节点

        //如果node的左子树为空,则直接删除node,并返回node的右子树
        if (node.left == null) {
            --size;
            return node.right;
        }

        //如果node的右子树为空,则直接删除node,并返回node的左子树
        if (node.right == null) {
            --size;
            return node.left;
        }

        //node的左、右子树均存在,则需要寻找node的后继节点——node的右子树中的最小节点
        Node successor = new Node(min(node.right));
        successor.left = node.left;
        successor.right = removeMin(node.right);    //因为removeMin中已经--size了,所以这里不用再处理了
        return successor;

    }

    //二叉搜索树的节点
    private class Node {
        K key;
        V value;
        Node left;
        Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node(Node node) {
            this.key = key;
            this.value = value;
            this.left = node.left;
            this.right = node.right;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = AlgorithmUtils.genRandomArray(20, -100, 100);
        BinarySearchTree<Integer, Integer> bst = new LinkedBinarySearchTree<>();

        for (Integer i : arr) {
            bst.insert(i, i);
        }

        bst.postOrder();
        bst.inOrder();
        bst.postOrder();
        bst.levelOrder();

        System.err.println("min: " + bst.min());
        System.err.println("max: " + bst.max());

        bst.removeMin();
        bst.removeMin();
        bst.inOrder();

        bst.removeMax();
        bst.removeMax();
        bst.inOrder();

        bst.remove(arr[5]);
        bst.remove(arr[6]);
        bst.inOrder();
    }
}
