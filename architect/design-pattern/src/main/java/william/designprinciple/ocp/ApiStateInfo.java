package william.designprinciple.ocp;

import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2021-01-11
 * @Description API 监控状态
 */
@Data
public class ApiStateInfo {
    private long timeCosts; //耗时

    private long successCount;  //成功数

    private long errorCount;    //异常数

    private long timeoutCount;  //超时数
}
