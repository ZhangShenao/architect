package william.algo.basic.queue;

/**
 * @Author zhangshenao
 * @Date 2021-11-10
 * @Description 基于数组实现的循环队列
 */
public class ArrayCircularQueue {
    private int[] arr;  //使用数组保存队列原始
    private int capacity;   //队列容量
    private int size;   //队列中元素数量
    private int head;   //队头索引
    private int tail;   //队尾索引

    //构造循环队列
    public ArrayCircularQueue(int capacity) {
        this.arr = new int[capacity];
        this.capacity = capacity;
        this.size = 0;
        this.head = 0;
        this.tail = 0;
    }

    //入队
    public boolean enqueue(int item) {
        if (isFull()) {
            return false;
        }
        arr[tail] = item;   //从队尾入队
        tail = (tail + 1) % capacity;
        ++size;
        return true;
    }

    //出队
    public Integer dequeue() {
        if (isEmpty()) {
            return null;
        }
        Integer item = arr[head];   //从队头出队
        head = (head + 1) % capacity;
        --size;
        return item;
    }

    //获取队列中的元素数量
    public int size() {
        return size;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return (head == tail);
    }

    //判断队列是否已满
    public boolean isFull() {
        return (head == (tail + 1) % capacity);
    }
}
