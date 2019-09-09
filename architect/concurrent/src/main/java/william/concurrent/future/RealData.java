package william.concurrent.future;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/28 14:09
 * @Description:
 */
public class RealData implements Data{
    public RealData(){
        //模拟耗时操作
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getResult(String param) {
        return "Result of " + param;
    }
}
