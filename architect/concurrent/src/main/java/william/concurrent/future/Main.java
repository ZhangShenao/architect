package william.concurrent.future;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/28 14:22
 * @Description:
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        RequestClient client = new RequestClient();

        //这里线程直接返回,不阻塞
        Data data = client.getData();

        //执行其他操作
        System.err.println("主线程执行其他操作");
        Thread.sleep(2000L);

        //获取实际数据
        System.err.println("Result: " + data.getResult("param"));
    }
}
