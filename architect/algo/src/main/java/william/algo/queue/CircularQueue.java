package william.algo.queue;

import java.util.Optional;

/**
 * @Author zhangshenao
 * @Date 2021-04-13
 * @Description 基于数组实现的循环队列
 */
public class CircularQueue<T> {
    private static final int DEFAULT_CAPACITY = 10; //队列默认容量

    private T[] items;  //保存队列中的元素

    private int head;   //队列头下标

    private int tail;   //队列尾下标

    public CircularQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.items = (T[]) new Object[capacity];
        this.head = this.tail = 0;
    }

    public CircularQueue() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 入队
     *
     * @param value 待入队的元素
     * @return 是否入队成功
     */
    public boolean offer(T value) {
        //如果队列已满,则直接返回
        if (isFull()) {
            return false;
        }

        //在队尾入队
        items[tail] = value;

        //修改队尾下标
        tail = ((tail + 1) % items.length);
        return true;
    }

    /**
     * 出队
     *
     * @return 出队的元素。如果队列为空，则返回Optional.empty()
     */
    public Optional<T> poll() {
        if (isEmpty()) {
            return Optional.empty();
        }

        //在队头出队
        T value = items[head];

        //修改队头下标
        head = ((head + 1) % items.length);

        return Optional.of(value);
    }

    public void printAll() {
        StringBuilder builder = new StringBuilder();
        int i = head;

        while ((i % items.length) != tail) {
            builder.append(i).append(",");
            ++i;
        }

        System.out.println(builder.append("\n"));
    }

    /**
     * 判断队列是否为空
     *
     * @return 队列是否为空
     */
    private boolean isEmpty() {
        return (head == tail);
    }

    /**
     * 判断队列是否已满
     *
     * @return 队列是否已满
     */
    private boolean isFull() {
        return (head == (tail + 1) % items.length);
    }

    public static void main(String[] args) {
        CircularQueue<Integer> queue = new CircularQueue<>(15);
        for (int i = 0; i < 10; i++) {
            queue.offer(i);
        }
        System.out.println("初始化: ");
        queue.printAll();

        queue.poll().ifPresent(x -> System.out.println("出队: " + x));
        queue.poll().ifPresent(x -> System.out.println("出队: " + x));
        queue.poll().ifPresent(x -> System.out.println("出队: " + x));

        System.out.println("出队后: ");
        queue.printAll();

    }
}
