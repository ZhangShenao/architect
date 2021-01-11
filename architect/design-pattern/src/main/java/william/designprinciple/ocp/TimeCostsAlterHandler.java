package william.designprinciple.ocp;

/**
 * @Author zhangshenao
 * @Date 2021-01-11
 * @Description 耗时报警处理器
 */
public class TimeCostsAlterHandler extends AbstractAlertHandler<TimeCostsAlertRule> {
    public TimeCostsAlterHandler(TimeCostsAlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStateInfo info) {
        if (info.getTimeoutCount() > getRule().alterThreshold()) {
            getNotification().trigger();
        }
    }
}
