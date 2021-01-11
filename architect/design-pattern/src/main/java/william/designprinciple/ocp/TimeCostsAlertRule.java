package william.designprinciple.ocp;

/**
 * @Author zhangshenao
 * @Date 2021-01-11
 * @Description 耗时报警规则
 */
public class TimeCostsAlertRule implements AlterRule {
    private double threshold;

    public TimeCostsAlertRule(double threshold) {
        this.threshold = threshold;
    }

    @Override
    public double alterThreshold() {
        return threshold;
    }
}
