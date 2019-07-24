package william.spring.proxy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/24 15:54
 * @Description:
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Worker engineer = new Engineer();
        InvocationHandler h = new WorkerInvocationHandler(engineer);
        Class<?> engineerClass = engineer.getClass();
        Worker proxy = (Worker) Proxy.newProxyInstance(engineerClass.getClassLoader(), engineerClass.getInterfaces(), h);
        //调用工程师类中的方法
        proxy.goToWork();
        proxy.goOffWork();

        //将我们生成的代理类engineerProxy中相关信息打印出来
        StringBuilder methodList = new StringBuilder();
        for (Method m : proxy.getClass().getDeclaredMethods()) {
            methodList.append(m.getName()).append("  ");
        }
        System.err.print("engineerProxy中的方法: " + methodList + "\n");
        System.err.print("engineerProxy的父类: " + proxy.getClass().getSuperclass() + "\n");
        String interfaces = "";
        for (Class<?> i : proxy.getClass().getInterfaces()) {
            interfaces += i.getName() + "  ";
        }
        System.err.print("engineerProxy中实现的接口: " + interfaces + "\n");
    }

}
