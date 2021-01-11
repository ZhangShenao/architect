package william.designprinciple.ocp;

/**
 * @Author zhangshenao
 * @Date 2021-01-11
 * @Description 报警客户端
 */
public class Client {
    public static void main(String[] args) {
        //创建报警类
        Alert alert = new Alert();

        //设置报警规则
        TimeCostsAlertRule rule = new TimeCostsAlertRule(200D);

        //设置报警通知
        Notification notification = new EmailNotification();

        //设置超时报警处理器
        alert.addHandler(new TimeCostsAlterHandler(rule, notification));

        //报警检测
        ApiStateInfo info = new ApiStateInfo();
        alert.check(info);
    }
}
