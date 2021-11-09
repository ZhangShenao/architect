package william.algo.leetcode.queue;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Author zhangshenao
 * @Date 2021-10-31
 * @Description
 */
public class 用队列实现栈_225 {
    class MyStack {
        //使用两个队列——主队列和附属队列
        private Queue<Integer> main = new ArrayDeque<>();  //主用于保存栈中的元素,保证队列头元素就是栈顶元素
        private Queue<Integer> aux = new ArrayDeque<>(); //附属队列主要用于元素的移动

        public MyStack() {

        }

        //压栈操作,时间复杂度O(n)
        public void push(int x) {
            //Step1:将新入栈元素放入附属队列
            aux.offer(x);

            //Step2:将主队列中的全部元素依次放入附属队列
            while (!main.isEmpty()) {
                aux.offer(main.poll());
            }

            //Step3:将主队列和附属队列互换,操作完成后附属队列为空
            Queue<Integer> tmp = main;
            main = aux;
            aux = tmp;
            tmp = null;
        }

        //弹栈操作,时间复杂度O(1)
        public int pop() {
            //直接将主队列队首元素出队即可
            return main.poll();
        }

        //获取栈顶元素,时间复杂度O(1)
        public int top() {
            //直接取主队列队首元素出队即可
            return main.peek();
        }

        public boolean empty() {
            //判断主队列和附属队列是否均为空
            return (main.isEmpty() && aux.isEmpty());
        }
    }
}
