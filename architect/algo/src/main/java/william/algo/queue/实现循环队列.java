package william.algo.queue;

/**
 * @Author zhangshenao
 * @Date 2021-09-07
 * @Description 实现循环队列
 * https://leetcode-cn.com/problems/design-circular-queue/description/
 * 入队、出队的时间复杂度均为O(1)
 */
public class 实现循环队列 {
    private int[] queue;    //使用数组保存队列中的元素
    private int head;   //维护队列头索引
    private int tail;   //维护队列尾索引（下一个待入队的位置）
    private int size;   //记录队列中元素的数量
    private int capacity;   //记录队列容量


    public 实现循环队列(int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("capacity must be positive!");
        }
        this.queue = new int[k];
        this.capacity = k;
        this.head = this.tail = this.size = 0;
    }

    //向循环队列插入一个元素。如果成功插入则返回真
    public boolean enQueue(int value) {
        //判断队列是否已满
        if (isFull()) {
            return false;
        }

        //在队尾入队
        queue[tail] = value;
        tail = (tail + 1) % capacity;
        ++size;
        return true;
    }

    //从循环队列中删除一个元素。如果成功删除则返回真。
    public boolean deQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            return false;
        }

        //从队头出队
        head = (head + 1) % capacity;
        --size;
        return true;
    }

    //从队首获取元素。如果队列为空，返回 -1 。
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return queue[head];
    }

    //获取队尾元素。如果队列为空，返回 -1 。
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        int idx = (tail - 1 + capacity) % capacity;
        return queue[idx];
    }

    public boolean isEmpty() {
        return (size <= 0);
    }

    public boolean isFull() {
        return (size >= capacity);
    }
}
