package william.jvm.tools;

/**
 * @Author zhangshenao
 * @Date 2020-07-05
 * @Description 通过jstack可以找到占用cpu最高的线程
 */
public class Math {
    public int calc(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        Math math = new Math();
        while (true) {
            math.calc(1, 2);
        }
    }
}
