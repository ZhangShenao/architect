package william.concurrent.future;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/28 14:08
 * @Description:
 */
public class FutureData implements Data {
    private RealData realData;

    private boolean ready = false;

    public synchronized void setRealData(RealData realData) {
        if (ready) {
            return;
        }
        this.realData = realData;
        ready = true;
        notifyAll();
    }

    @Override
    public synchronized String getResult(String param) {
        //在数据准备好之前,阻塞调用线程
        while (!ready) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.getResult(param);
    }
}
