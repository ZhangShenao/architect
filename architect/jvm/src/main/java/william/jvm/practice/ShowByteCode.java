package william.jvm.practice;

/**
 * @Author zhangshenao
 * @Date 2021-07-14
 * @Description javap -c -verbose 类名：查看Class字节码
 */
public class ShowByteCode {
    public static void main(String[] args) {
        ShowByteCode sbc = new ShowByteCode();
        sbc.cacl();
    }

    private void cacl() {
        int a = 1;
        int b = 2;
        double c = a + b;
        double d = a * b;

        if (a > b) {
            System.out.println("A > B");
        }

        for (int i = 0; i < 10; i++) {
            ++a;
        }
    }
}
