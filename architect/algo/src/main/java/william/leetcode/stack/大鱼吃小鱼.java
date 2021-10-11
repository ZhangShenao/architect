package william.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author zhangshenao
 * @Date 2021-09-05
 * @Description
 */
public class 大鱼吃小鱼 {
    //时间复杂度O(n):每条鱼都只入栈、出栈一次
    //空间复杂度O(n):最坏情况下所有鱼都会入栈
    public static int fishEating(int[] size, int[] direction) {
        //边界条件
        if (size.length != direction.length) {
            throw new IllegalArgumentException();
        }

        if (size.length <= 0) {
            return 0;
        }

        if (size.length == 1) {
            return 1;
        }

        //使用一个栈保存所有存活的鱼的索引
        Deque<Integer> alive = new ArrayDeque<>(size.length);

        for (int i = 0; i < size.length; i++) {
            int curSize = size[i];  //记录当前鱼的大小
            int curDir = direction[i];  //记录当前鱼的方向
            boolean eaten = false;  //记录当前鱼是否被吃掉了


            //把当前鱼与栈中的鱼逐一比较,如果当前的鱼比栈顶的鱼大,则把它吃掉
            while (!alive.isEmpty() && direction[alive.peek()] != curDir) {
                //如果当前鱼比栈顶的鱼小,那么当前鱼就被吃掉了,本轮结束
                if (curSize < size[alive.peek()]) {
                    eaten = true;
                    break;
                }

                //如果当前鱼比栈顶的鱼大,则把栈顶的鱼吃掉
                alive.pop();
            }

            //如果当前鱼活下来了,则入栈
            if (!eaten) {
                alive.push(i);
            }
        }

        //最后返回存活的鱼的数量
        return alive.size();
    }

    public static void main(String[] args) {
        int[] size = new int[] {4, 2, 5, 3, 1};
        int[] direction = new int[] {1, 1, 0, 0, 0};
        System.err.println(fishEating(size, direction));
    }
}
