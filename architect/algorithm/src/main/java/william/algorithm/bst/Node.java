package william.algorithm.bst;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/16 16:10
 * @Description:
 */
public class Node{
    int key;
    int value;
    Node left;
    Node right;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public Node copy(){
        Node result = new Node(this.key,this.value);
        result.left = this.left;
        result.right = this.right;
        return result;
    }
}
