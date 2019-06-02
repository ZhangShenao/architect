package william.ds.stack;


/**
 * @Author: ZhangShenao
 * @Date: 2019/6/2 10:03
 * @Description:基于链表实现的栈
 */
public class LinkedStack<E> implements Stack<E> {
    //栈顶节点
    private Node<E> top;

    //记录栈中元素的数量
    private int size;

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void push(E e) {
        top = new Node<>(e, top);
        ++size;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E e = top.data;
        top = top.next;
        --size;
        return e;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return top.data;
    }

    private static class Node<E> {
        E data;
        Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }
}
