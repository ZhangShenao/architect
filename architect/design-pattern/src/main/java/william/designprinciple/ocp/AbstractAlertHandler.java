package william.designprinciple.ocp;

/**
 * @Author zhangshenao
 * @Date 2021-01-11
 * @Description 抽象报警处理器
 */
public abstract class AbstractAlertHandler<T extends AlterRule> {
    private T rule;

    private Notification notification;

    public AbstractAlertHandler(T rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }

    public T getRule() {
        return rule;
    }

    public Notification getNotification() {
        return notification;
    }

    //检查监控指标,判断是否需要触发报警
    public abstract void check(ApiStateInfo info);
}
