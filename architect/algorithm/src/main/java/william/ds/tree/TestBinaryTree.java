package william.ds.tree;

import org.junit.Test;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/5 16:29
 * @Description:二叉树测试
 */
public class TestBinaryTree {
    @Test
    public void testPrint() {
        BinaryTree tree = BinaryTree.fromStr("[4,2,7,1,3,5]");
        tree.print();
    }

    @Test
    public void testPreOrder(){
        BinaryTree tree = BinaryTree.fromStr("[4,2,7,1,3,5,10]");
        tree.print();
        tree.preOrderRecursive();
    }

    @Test
    public void testLevelOrder(){
        BinaryTree tree = BinaryTree.fromStr("[4,2,7,1,3,5,10]");
        tree.print();
        tree.levelOrder();
    }
}
