package william.ds.tree;

import java.util.*;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/5 16:00
 * @Description:二叉树的二叉链表实现
 */
public class BinaryTree {
    //根节点
    private Node root;

    //非递归前序遍历,利用栈
    public void preOrder() {
        if (root == null) {
            return;
        }

        List<Integer> result = new LinkedList<>();
        Deque<Node> stack = new LinkedList<>();

        //首先将根节点入栈
        stack.push(root);

        while (!stack.isEmpty()) {
            //打印栈顶元素
            Node n = stack.pop();
            result.add(n.data);

            //先将右子节点入栈
            if (n.right != null) {
                stack.push(n.right);
            }

            //再将左子节点入栈
            if (n.left != null) {
                stack.push(n.left);
            }
        }

        System.err.println(result);
    }

    public void preOrderRecursive(){
        LinkedList<Integer> result = new LinkedList<>();
        doPreOrderRecursive(root,result);
        System.err.println(result);
    }

    public void doPreOrderRecursive(Node n,List<Integer> result){
        if (n == null){
            return;
        }
        result.add(n.data);
        doPreOrderRecursive(n.left,result);
        doPreOrderRecursive(n.right,result);
    }

    //层序遍历,并每层单独打印一行
    public void levelOrder(){
        if (root == null){
            return;
        }

        //借助队列
        Queue<Node> queue = new LinkedList<>();

        //先将根节点入队
        queue.offer(root);
        
        //开始遍历
        while (!queue.isEmpty()){
            //记录当前队列中的元素数量,当遍历到指定数量后,打印一个换行
            int size = queue.size();

            for (int i = 0;i < size;i++){
                Node node = queue.poll();

                //先遍历当前节点
                System.err.print(" " + node.data + " ");

                //然后依次将左、右两个子节点入队
                if (node.left != null){
                    queue.offer(node.left);
                }

                if (node.right != null){
                    queue.offer(node.right);
                }
            }

            //打印换行
            System.err.println();
        }
    }

    public static BinaryTree fromStr(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        Node root = new Node(Integer.parseInt(item));
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while (!nodeQueue.isEmpty()) {
            Node node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new Node(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new Node(rightNumber);
                nodeQueue.add(node.right);
            }
        }

        BinaryTree tree = new BinaryTree();
        tree.root = root;
        return tree;
    }

    public static String integerArrayListToString(List<Integer> nums, int length) {
        if (length == 0) {
            return "[]";
        }

        String result = "";
        for (int index = 0; index < length; index++) {
            Integer number = nums.get(index);
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayListToString(List<Integer> nums) {
        return integerArrayListToString(nums, nums.size());
    }

    public void print() {
        int maxLevel = maxLevel(root);
        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }


    private void printNodeInternal(List<Node> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        printWhitespaces(firstSpaces);

        List<Node> newNodes = new ArrayList<>();
        for (Node node : nodes) {
            if (node != null) {
                System.err.print(node.data);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.err.print(" ");
            }

            printWhitespaces(betweenSpaces);
        }
        System.err.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.err.print("/");
                else
                    printWhitespaces(1);

                printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.err.print("\\");
                else
                    printWhitespaces(1);

                printWhitespaces(endgeLines + endgeLines - i);
            }

            System.err.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.err.print(" ");
    }

    private int maxLevel(Node node) {
        if (node == null)
            return 0;

        return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
    }

    private boolean isAllElementsNull(List<Node> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }
        return true;
    }

    //树的节点
    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}
