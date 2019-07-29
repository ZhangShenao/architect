package william.computerprinciple.cache;

import java.util.concurrent.ThreadLocalRandom;

/**
 * <p>测试双向链表 </p>
 *
 * @author ZhangShenao
 * @date 2018年4月12日
 */
public class TestDoubleLinkedList {
    public static void main(String[] args) {
        int capacity = 10;
        ThreadLocalRandom random = ThreadLocalRandom.current();
        DoubleLinkedList<Integer, Integer> list = new DoubleLinkedList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            int num = random.nextInt(1000);
            list.addLast(num,num);
        }

        System.err.println(list);
    }
}
