package william.ds.queue;


/**
 * @Author: ZhangShenao
 * @Date: 2019/6/2 10:29
 * @Description:基于链表实现的队列
 */
public class LinkedQueue<E> implements Queue<E> {
    //队头节点
    private Node<E> head;

    //队尾节点
    private Node<E> tail;

    //记录栈中元素的数量
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public void offer(E e) {
        //offer to empty queue
        if (isEmpty()) {
            head = tail = new Node(e, null);
            ++size;
            return;
        }

        Node<E> n = new Node(e, null);
        tail.next = n;
        tail = n;
        ++size;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E e = head.data;
        head = head.next;
        --size;
        return e;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return head.data;
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
