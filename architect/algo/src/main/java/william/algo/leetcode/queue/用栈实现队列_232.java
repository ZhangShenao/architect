package william.algo.leetcode.queue;

import java.util.Stack;

/**
 * @Author zhangshenao
 * @Date 2021-10-31
 * @Description
 */
public class 用栈实现队列_232 {
    class MyQueue {
        private Stack<Integer> input = new Stack<>();
        private Stack<Integer> output = new Stack<>();

        public MyQueue() {

        }

        public void push(int x) {
            //入队,放入输入栈
            input.push(x);
        }

        public int pop() {
            //如果输出栈不为空,优先从输出栈弹栈
            if (!output.isEmpty()) {
                return output.pop();
            }
            transfer();

            //从输出栈弹出元素
            return output.pop();
        }

        public int peek() {
            //如果输出栈不为空,优先从输出栈弹栈
            if (!output.isEmpty()) {
                return output.peek();
            }
            transfer();
            return output.peek();
        }

        public boolean empty() {
            //需要校验输入栈和输出栈是否都为空
            return (output.isEmpty() && input.isEmpty());
        }

        //将输入栈中的元素一次性转移到输出栈
        private void transfer() {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        }
    }
}
