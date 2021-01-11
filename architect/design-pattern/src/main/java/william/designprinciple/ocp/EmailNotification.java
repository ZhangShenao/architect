package william.designprinciple.ocp;

/**
 * @Author zhangshenao
 * @Date 2021-01-11
 * @Description 邮件通知
 */
public class EmailNotification implements Notification{
    @Override
    public void trigger() {
        System.err.println("发送邮件");
    }
}
