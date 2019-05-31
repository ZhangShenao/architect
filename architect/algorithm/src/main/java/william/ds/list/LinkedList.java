package william.ds.list;

/**
 * @Author: ZhangShenao
 * @Date: 2019/5/31 10:44
 * @Description:基于链表实现的线性表
 */
public class LinkedList<E> implements List<E> {
    //链表的第一个节点
    private Node<E> first;

    //记录当前线性表中元素个数
    private int size;

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean contains(E e) {
        return (indexOf(e) >= 0);
    }

    @Override
    public int indexOf(E e) {
        Node<E> n = first;
        int idx = 0;
        while (n != null) {
            if (n.data.equals(e)) {
                return idx;
            }
            n = n.next;
            ++idx;
        }
        return -1;
    }

    @Override
    public E get(int index) {
        return node(index).data;
    }

    @Override
    public void set(int index, E e) {
        checkIndex(index);
        Node<E> n = first;
        for (int i = 0; i < index; i++) {
            n = n.next;
        }
        n.data = e;
    }

    @Override
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index out of bounds: " + index);
        }

        //空链表插入
        if (first == null) {
            addToEmptyList(e);
            return;
        }

        //在头部插入
        if (index == 0) {
            addFirst(e);
            return;
        }

        //在尾部插入
        if (index == size) {
            addLast(e);
            return;
        }

        //在中间插入,找到前驱节点
        Node<E> prev = node(index - 1);
        Node<E> cur = new Node<>(e, prev.next);
        prev.next = cur;
        ++size;

    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        if (first == null) {
            return null;
        }

        //删除头结点
        if (index == 0) {
            return removeFirst();
        }

        //删除尾节点
        if (index == size - 1) {
            return removeLast();
        }

        //删除中间节点,找到前驱节点
        Node<E> prev = node(index - 1);
        Node<E> cur = prev.next;
        E e = cur.data;
        prev.next = cur.next;
        cur = null;
        --size;
        return e;
    }

    @Override
    public void addLast(E e) {
        if (first == null) {
            addToEmptyList(e);
            return;
        }

        Node<E> last = first;
        while (last.next != null) {
            last = last.next;
        }
        last.next = new Node<>(e, null);
        ++size;
    }

    @Override
    public void addFirst(E e) {
        if (first == null) {
            addToEmptyList(e);
            return;
        }
        first = new Node<>(e, first);
        ++size;
    }

    @Override
    public E removeFirst() {
        if (first == null) {
            return null;
        }
        E e = first.data;
        first = first.next;
        --size;
        return e;
    }

    @Override
    public E removeLast() {
        if (first == null) {
            return null;
        }
        if (first.next == null) {
            E e = first.data;
            first = null;
            return e;
        }

        //找到尾节点的前驱
        Node<E> prev = first;
        while (prev.next.next != null) {
            prev = prev.next;
        }
        E e = prev.next.data;
        prev.next = null;
        --size;
        return e;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("LinkedList [");
        Node<E> n = first;
        for (int i = 0; i < size - 1; i++) {
            s.append(n.data).append(",");
            n = n.next;
        }
        return s.append(n.data).append("]").toString();
    }

    private Node<E> node(int index) {
        checkIndex(index);
        Node<E> n = first;
        for (int i = 0; i < index; i++) {
            n = n.next;
        }
        return n;
    }


    private void addToEmptyList(E e) {
        first = new Node<>(e, null);
        ++size;
    }

    private static class Node<E> {
        E data;
        Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index out of bounds: " + index);
        }
    }
}
