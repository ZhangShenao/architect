package william.algo.stack;

import java.util.Optional;

/**
 * @Author zhangshenao
 * @Date 2021-04-13
 * @Description 基于链表实现的链式栈
 */
public class LinkedStack<T> {
    private Node<T> top;  //记录栈顶元素

    /**
     * 入栈
     *
     * @param value 要入栈的元素值
     */
    public void push(T value) {
        //入栈，直接修改栈顶元素
        top = new Node<>(value, top);
    }

    /**
     * 出栈
     *
     * @return 栈顶元素，可能为空
     */
    public Optional<T> pop() {
        if (top == null) {
            return Optional.empty();
        }

        T data = top.data;
        top = top.next;
        return Optional.of(data);
    }

    public void printAll() {
        Node<T> p = top;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    /**
     * 链表的节点
     */
    private static class Node<T> {
        //节点的数据
        private T data;

        //后继节点指针
        private Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public Node() {
        }
    }

    public static void main(String[] args) {
        LinkedStack<Integer> stack = new LinkedStack<>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }

        System.err.println("栈初始化: ");
        stack.printAll();

        stack.pop();
        stack.pop();
        stack.pop();

        System.err.println("出栈后: ");
        stack.printAll();
    }

}
