package william.concurrent.queue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/25 18:40
 * @Description:
 */
public class TestPriorityBlockingQueue {
    public static void main(String[] args) {
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();
        queue.add(4);
        queue.add(2);
        queue.add(1);
        queue.add(3);

        //直接遍历,数据是无序的
//        queue.forEach(System.err::println);

        //使用poll方法,可以保证安装优先级顺序出队
        while (!queue.isEmpty()) {
            System.err.println(queue.poll());
        }
    }
}
