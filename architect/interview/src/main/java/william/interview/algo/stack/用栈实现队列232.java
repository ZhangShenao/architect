package william.interview.algo.stack;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 */
public class 用栈实现队列232 {
    class MyQueue {
        //使用两个栈:一个输入栈,一个输出栈
        //push元素时放入输入栈,pop元素时从输出栈pop
        //如果输出栈为空,则先将输入栈元素移动到输出栈
        private Stack<Integer> in;
        private Stack<Integer> out;

        public MyQueue() {
            this.in = new Stack<>();
            this.out = new Stack<>();
        }

        public void push(int x) {
            //push元素:直接放入输入栈
            in.push(x);
        }

        public int pop() {
            //pop元素:从输出栈pop
            //如果输出栈为空,先将输入栈中的元素一次性移动到输出栈
            if (out.isEmpty()) {
                transfer();
            }

            return out.pop();
        }

        public int peek() {
            //peek元素:从输出栈peek
            //如果输出栈为空,先将输入栈中的元素一次性移动到输出栈
            if (out.isEmpty()) {
                transfer();
            }

            return out.peek();
        }

        public boolean empty() {
            //当两个栈都为空时,整个队列为空
            return (in.isEmpty()) && (out.isEmpty());
        }


        private void transfer() {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
    }
}
