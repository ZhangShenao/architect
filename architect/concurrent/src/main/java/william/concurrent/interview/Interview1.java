package william.concurrent.interview;

import java.util.LinkedList;
import java.util.List;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/26 10:08
 * @Description:有两个线程A、B，A线程向一个集合(List<String>) 里面依次添加元素“abc”字符串, 一共添加十次，当添加到第五次的时候，
 * 希望B线程能够收到A线程的通知，然后B线程执行相关的业务操作，我们应该如何进行设计？
 */
public class Interview1 {
    //使用volatile修饰,保证了变量在多个线程间的可见性,只要strs中元素达到5个,线程B就可以立即收到通知
    private static volatile List<String> strs = new LinkedList<>();

    public static void main(String[] args) {
        Thread A = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                String str = String.valueOf((char)('a' + i));
                strs.add(str);
                System.err.println("线程A向集合中添加元素:" + str);
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread B = new Thread(() -> {
            while (true) {
                if (strs.size() == 5) {
                    System.err.println("集合中元素达到5个,B线程退出");
                    throw new RuntimeException();
                }
            }
        });

        B.start();
        A.start();
    }
}
