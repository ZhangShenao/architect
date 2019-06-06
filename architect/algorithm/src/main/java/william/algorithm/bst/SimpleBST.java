package william.algorithm.bst;

import william.algorithm.util.RandomArrayGenerator;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/16 16:10
 * @Description:二叉搜索树，插入、删除和查找的时间复杂度均为O(logn)
 * 由于BST并非完全二叉树, 所以更适合采用链表实现
 */
public class SimpleBST {
    private int size;
    private Node root;

    public Node insert(int key, int value) {
        this.root = doInsert(root, key, value);
        return this.root;
    }

    public boolean contains(int key) {
        return doContains(root, key);
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void inOrder() {
        doInOrder(this.root);
    }

    public void preOrder() {
        doPreOrder(this.root);
    }

    public void postOrder() {
        doPostOrder(this.root);
    }

    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(this.root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.err.print(node.key + " ");
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    public int getMaxValue() {
        return getMaxNode(this.root).value;
    }

    public int getMinValue() {
        return getMinNode(this.root).value;
    }

    public void deleteMax() {
        this.root = doDeleteMax(this.root);
    }

    public void deleteMin() {
        this.root = doDeleteMin(this.root);
    }

    private Node doDeleteMin(Node node) {
        if (node.left == null) {
            size--;
            return node.right;
        }
        node.left = doDeleteMin(node.left);
        return node;
    }

    public void deleteNode(int key) {
        this.root = doDeleteNode(this.root, key);
    }

    private Node doDeleteNode(Node node, int key) {
        if (node == null) {
            return null;
        }

        //Delete Node with Only One Child or without Child
        if (node.left == null) {
            size--;
            return node.right;
        }
        if (node.right == null) {
            size--;
            return node.left;
        }

        //Delete Node with Two Children
        if (key > node.key) {
            node.right = doDeleteNode(node.right, key);
            return node;
        }

        if (key < node.key) {
            node.left = doDeleteNode(node.left, key);
            return node;
        }

        //Find Min in Right Sub-Tree
        Node successor = getMinNode(node.right).copy();   //No Null Definitely
        successor.right = doDeleteMin(node.right);
        size++;
        successor.left = node.left;
        size--;
        return successor;
    }

    private Node doDeleteMax(Node node) {
        if (node.right == null) {
            size--;
            return node.left;
        }
        node.right = doDeleteMax(node.right);
        return node;
    }

    private Node getMinNode(Node node) {
        if (node.left == null) {
            return node;
        }
        return getMinNode(node.left);
    }

    private Node getMaxNode(Node node) {
        if (node.right == null) {
            return node;
        }
        return getMaxNode(node.right);
    }

    private void doPostOrder(Node node) {
        if (node == null) {
            return;
        }
        doPostOrder(node.left);
        doPostOrder(node.right);
        System.err.print(node.key + " ");
    }

    private void doPreOrder(Node node) {
        if (node == null) {
            return;
        }
        System.err.print(node.key + " ");
        doPreOrder(node.left);
        doPreOrder(node.right);
    }

    private Node doInsert(Node node, int key, int value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        if (node.key == key) {
            node.value = value;
            return node;
        }
        if (key > node.key) {
            node.right = doInsert(node.right, key, value);
        } else {
            node.left = doInsert(node.left, key, value);
        }
        return node;
    }

    private boolean doContains(Node node, int key) {
        if (node == null) {
            return false;
        }
        if (node.key == key) {
            return true;
        }
        if (key > node.key) {
            return doContains(node.right, key);
        }
        return doContains(node.left, key);
    }

    private void doInOrder(Node node) {
        if (node == null) {
            return;
        }
        doInOrder(node.left);
        System.err.print(node.key + " ");
        doInOrder(node.right);
    }

    public static void main(String[] args) {
        SimpleBST simpleBst = new SimpleBST();
        int len = 10;
        int[] array = RandomArrayGenerator.generateRandomArray(len, -100, 100);
        System.err.println("arr: " + Arrays.toString(array));
        for (int i = 0; i < len; i++) {
            simpleBst.insert(array[i], array[i]);
        }
//        System.err.println("size: " + bst.size());
        System.err.println("InOrder:");
        simpleBst.inOrder();
        System.err.println();
        /*System.err.println("PreOrder:");
        bst.preOrder();
        System.err.println();
        System.err.println("PostOrder:");
        bst.postOrder();
        System.err.println();
        System.err.println("LevelOrder:");
        bst.levelOrder();
        System.err.println();*/
        /*System.err.println("Max Value: " + bst.getMaxValue());
        System.err.println("Min Value: " + bst.getMinValue());
        System.err.println("Delete Max: ");
        bst.removeMax();
        bst.inOrder();
        System.err.println();
        System.err.println("Delete Min: ");
        bst.removeMin();
        bst.inOrder();
        System.err.println();*/
        System.err.println("Delete Key: " + array[5]);
        simpleBst.deleteNode(array[5]);
        simpleBst.inOrder();
        System.err.println();
    }
}
