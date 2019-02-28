package william.concurrent.delayqueue;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/25 22:17
 * @Description:
 */
public class TestInternetBar {
    public static void main(String[] args) {
        InternetBar bar = new InternetBar();
        new Thread(bar).start();
        bar.newCustomer("001","Kobe",2);
        bar.newCustomer("002","James",5);
        bar.newCustomer("003","Curry",10);
    }
}
