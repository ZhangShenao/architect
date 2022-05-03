package william.leetcode.queue;

/**
 * https://leetcode-cn.com/problems/design-circular-queue/description/
 */
public class 设计循环队列_622 {
    class MyCircularQueue {
        private int[] arr;  //采用k+1长度的数组维护队列元素(有一个多余的空间是浪费的)
        private int front;  //队列头指针,指向队头元素
        private int rear;   //队列尾指针,指向下一个入队的位置

        public MyCircularQueue(int k) {
            if (k <= 0) {
                throw new IllegalArgumentException("k myst be positive");
            }


            //创建数组
            this.arr = new int[k + 1];

            //初始化front、rear指针
            this.front = this.rear = 0;
        }

        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            }

            //从队尾入队,维护rear指针
            arr[rear] = value;
            rear = (rear + 1) % arr.length;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            }

            //从队头出队,维护front指针
            front = (front + 1) % arr.length;
            return true;
        }

        public int Front() {
            if (isEmpty()) {
                return -1;
            }
            return arr[front];
        }

        public int Rear() {
            if (isEmpty()) {
                return -1;
            }
            return arr[(rear - 1 + arr.length) % arr.length];
        }

        public boolean isEmpty() {
            return (front == rear);
        }

        public boolean isFull() {
            return front == ((rear + 1) % arr.length);
        }
    }
}
