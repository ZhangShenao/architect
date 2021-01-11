package william.designprinciple.ocp;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangshenao
 * @Date 2021-01-10
 * @Description 报警类
 */
public class Alert {
    private List<AbstractAlertHandler> handlers = new ArrayList<>();

    public void addHandler(AbstractAlertHandler h) {
        handlers.add(h);
    }

    public void check(ApiStateInfo info) {
        handlers.forEach(x -> x.check(info));
    }
}
