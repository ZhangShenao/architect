package william.enhance.bst;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/21 09:35
 * @Description:二叉搜索树 ADT
 */
public interface BinarySearchTree<K extends Comparable<K>, V> {
    int size();

    boolean isEmpty();

    boolean contains(K key);

    void insert(K key, V value);

    V search(K key);

    void preOrder();

    void inOrder();

    void postOrder();

    void levelOrder();

    K min();

    K max();

    void removeMin();

    void removeMax();

    void remove(K key);
}
