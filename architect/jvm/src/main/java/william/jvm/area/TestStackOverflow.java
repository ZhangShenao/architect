package william.jvm.area;

/**
 * @Author zhangshenao
 * @Date 2020-06-15
 * @Description 测试栈溢出
 * -Xss128k
 */
public class TestStackOverflow {
    private static int count = 0;

    private static void incr() {
        ++count;
        incr();
    }

    public static void main(String[] args) {
        try {
            incr();
        } catch (Throwable e) {
            e.printStackTrace();
            System.err.println("count: " + count);
        }
    }
}
