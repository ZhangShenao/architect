package william.spring.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/24 15:52
 * @Description:
 */
public class WorkerInvocationHandler implements InvocationHandler{
    private Worker worker;

    public WorkerInvocationHandler(Worker worker) {
        this.worker = worker;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = format.format(new Date());
        System.err.println("当前时间: " + date);
        return method.invoke(worker, args);
    }
}
