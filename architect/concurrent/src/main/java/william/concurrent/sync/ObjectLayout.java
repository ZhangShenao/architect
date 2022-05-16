package william.concurrent.sync;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/16 下午2:25
 * <p>
 * 对象内存布局:
 * 对象头 Object Header
 * 实例数据 Instance Data
 * 对齐填充 Padding: 对象起始地址必须是8字节的整数倍
 * <p>
 * 对象头:
 * MarkWord
 * Klasss Pointer 类型指针
 * Array Length 数组长度
 * <p>
 * MarkWord:
 * hashCode
 * 分代年龄
 * 偏向锁标识
 * 锁状态标识
 * 偏向锁(线程)ID
 * <p>
 * synchronized的monitor锁标识就保存在对象头MarkWord中
 * <p>
 * 锁状态:
 * 偏向锁:101
 * 无锁:01
 * 轻量级锁:00
 * 重量级锁:10
 * <p>
 * JVM对synchronized的优化
 * 偏向锁
 * 轻量级锁+CAS
 * 重量级锁+自适应自旋,最后才会park阻塞线程
 */
public class ObjectLayout {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000L);
        //创建锁对象
        Lock lock = new Lock();

        //查看对象内存布局
        System.out.println("初始化状态");    //101 偏向锁——匿名偏向(anonymously biased)
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("线程T1获取锁");    //101 偏向锁
                System.out.println(ClassLayout.parseInstance(lock).toPrintable());
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(2000L);

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("线程T2获取锁");    //00 轻量级锁
                System.out.println(ClassLayout.parseInstance(lock).toPrintable());
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("线程T3获取锁");    //10 重量级锁
                System.out.println(ClassLayout.parseInstance(lock).toPrintable());
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(Long.MAX_VALUE);
    }

    static class Lock {
        private int i;
    }
}
