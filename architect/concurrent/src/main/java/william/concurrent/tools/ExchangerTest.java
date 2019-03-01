package william.concurrent.tools;

import java.util.concurrent.Exchanger;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/28 14:41
 * @Description:Exchanger用于在两个线程之间交换变量
 */
public class ExchangerTest {
    private static final Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(() -> {
            String A = "A";
            try {
                String B = exchanger.exchange(A);
                System.err.println("线程A: " + B);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            String B = "B";
            try {
                String A = exchanger.exchange(B);
                System.err.println("线程B: " + A);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
