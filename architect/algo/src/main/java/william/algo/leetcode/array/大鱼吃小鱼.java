package william.algo.leetcode.array;

import java.util.Stack;

/**
 * @Author zhangshenao
 * @Date 2021-10-18
 * @Description https://app.codility.com/programmers/lessons/7-stacks_and_queues/fish/start/
 */
public class 大鱼吃小鱼 {
    public int solution(int[] fishSize, int[] fishDirection) {
        //边界条件
        if (fishSize == null || fishSize.length == 0 || fishDirection == null || fishDirection.length == 0) {
            return 0;
        }

        //使用栈保存存活的鱼的索引
        Stack<Integer> alive = new Stack<>();
        for (int i = 0; i < fishSize.length; i++) {
            boolean eaten = false;  //记录当前鱼是否被吃掉了
            int curSize = fishSize[i];
            int curDir = fishDirection[i];
            while (!alive.isEmpty() && curDir != fishDirection[alive.peek()]) {
                int aliveSize = fishSize[alive.peek()];
                int aliveDir = fishDirection[alive.peek()];
                //如果栈顶的鱼比当前的鱼大，且方向不同，则当前的鱼被吃掉
                if (aliveSize > curSize && aliveDir != curDir) {
                    eaten = true;
                    break;
                }

                //如果当前与比栈顶的鱼大，且方向不同，则栈顶的鱼被吃掉
                if (curSize > aliveSize && aliveDir != curDir) {
                    alive.pop();
                }
            }

            //如果当前鱼没有被吃掉,则入栈
            if (!eaten) {
                alive.push(i);
            }
        }

        //返回存活的鱼的数量
        return alive.size();
    }

    public static void main(String[] args) {
        大鱼吃小鱼 fish = new 大鱼吃小鱼();
        int[] size = new int[] {4, 2, 5, 1, 3};
        int[] dir = new int[] {1, 1, 0, 0, 0};
        System.err.println(fish.solution(size, dir));
    }
}
