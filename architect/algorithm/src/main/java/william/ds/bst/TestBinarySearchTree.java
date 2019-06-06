package william.ds.bst;

import org.junit.Test;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/6 13:54
 * @Description:二叉搜索树测试
 */
public class TestBinarySearchTree {
    @Test
    public void testInsert(){
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0;i < 10;i++){
            int rand = random.nextInt(-100, 100);
            bst.insert(rand,rand);
        }
        bst.inOrder();
    }

    @Test
    public void testGet(){
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0;i < 10;i++){
            int rand = random.nextInt(-100, 100);
            bst.insert(rand,rand);
        }

        for (int i = 0;i < 100;i++){
            int rand = random.nextInt(-100, 100);
            System.err.println(bst.get(rand));
        }
    }

    @Test
    public void testMaxAndMin(){
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0;i < 10;i++){
            int rand = random.nextInt(-100, 100);
            bst.insert(rand,rand);
        }
        bst.inOrder();
        System.err.println("max: " + bst.max());
        System.err.println("min: " + bst.min());
    }

    @Test
    public void testDeleteMinAndMax(){
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0;i < 10;i++){
            int rand = random.nextInt(-100, 100);
            bst.insert(rand,rand);
        }
        bst.inOrder();
        bst.removeMin();
        bst.removeMin();
        bst.removeMax();
        bst.removeMax();
        bst.inOrder();
    }

    @Test
    public void testRemove(){
        int size = 10;
        int[] keys = new int[size];
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0;i < size;i++){
            int rand = random.nextInt(-100, 100);
            keys[i] = rand;
            bst.insert(rand,rand);
        }
        bst.inOrder();

        for (int i = 0;i < size / 2;i++){
            int key = keys[i];
            bst.remove(key);
            System.err.println("remove: " + key);
        }

        bst.inOrder();
    }
}
