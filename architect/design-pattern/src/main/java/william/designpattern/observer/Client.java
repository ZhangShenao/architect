package william.designpattern.observer;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author zhangshenao
 * @Date 2020-03-23
 * @Description 观察者模式: 定义对象间一对多的依赖关系,使得当一个主题对象的状态发生转变时,依赖与它的对象都可以收到通知并且自动更新
 * 核心:将观察者与被观察者解耦
 */
public class Client {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(ObserverConfig.class);
        Star star = applicationContext.getBean(Star.class);
        star.action();
    }
}
