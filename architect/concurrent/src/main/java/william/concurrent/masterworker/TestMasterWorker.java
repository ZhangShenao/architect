package william.concurrent.masterworker;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/28 22:50
 * @Description:实际要处理的任务类
 */
public class TestMasterWorker {
    public static void main(String[] args) {
        int taskNum = 20;
        Master<String> master = new Master<>(10, taskNum);
        for(int i = 0;i < taskNum;i++){
            master.submit(() -> "result");
        }
    }
}
