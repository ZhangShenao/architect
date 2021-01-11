package william.designprinciple.ocp;

/**
 * @Author zhangshenao
 * @Date 2021-01-11
 * @Description 报警规则
 */
public interface AlterRule {
    //触发报警的阈值
    double alterThreshold();
}
