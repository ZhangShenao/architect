package william.leetcode.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/implement-stack-using-queues/
 */
public class 用队列实现栈225 {
    class MyStack {
        //使用两个队列,一个主队列和一个辅助队列
        //主队列用于存放元素,辅助队列用于元素的移动
        private Queue<Integer> major;
        private Queue<Integer> aux;

        public MyStack() {
            this.major = new LinkedList<>();
            this.aux = new LinkedList<>();
        }

        public void push(int x) {
            //入栈——直接入到主队列
            major.offer(x);
        }

        public int pop() {
            //出栈——将主队列中前面的元素都移动到辅助队列,将最后一个元素出栈,然后再移动回来
            if (major.isEmpty()) {
                return -1;
            }

            while (major.size() > 1) {
                aux.offer(major.poll());
            }

            int top = major.poll();
            while (!aux.isEmpty()) {
                major.offer(aux.poll());
            }

            return top;
        }

        public int top() {
            //出栈——将主队列中前面的元素都移动到辅助队列,将最后一个元素出栈,然后再移动回来
            if (major.isEmpty()) {
                return -1;
            }

            while (major.size() > 1) {
                aux.offer(major.poll());
            }

            int top = major.poll();
            aux.offer(top);
            while (!aux.isEmpty()) {
                major.offer(aux.poll());
            }

            return top;
        }

        public boolean empty() {
            //直接判断主队列是否为空
            return (major.isEmpty());
        }
    }
}
