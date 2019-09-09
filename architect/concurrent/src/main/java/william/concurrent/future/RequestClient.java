package william.concurrent.future;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/28 14:18
 * @Description:
 */
public class RequestClient {
    public Data getData(){
        //直接返回FutureData对象,创建线程异步获取数据,线程不阻塞
        FutureData futureData = new FutureData();
        new Thread(() -> {
            RealData realData = new RealData();
            futureData.setRealData(realData);
        }).start();
        return futureData;
    }
}
