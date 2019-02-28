package william.concurrent.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/20 16:20
 * @Description:AtomicReference,可以对一个引用类型变量进行CAS原子性的修改
 */
public class AtomicReferenceTest {
    private static final AtomicReference<User> reference = new AtomicReference<>();

    public static void main(String[] args) throws InterruptedException {
        User originUser = new User();
        originUser.setName("Kobe");
        originUser.setAge(38);
        reference.set(originUser);


        Thread t1 = new Thread(new ChangeAgeTask(originUser), "T1");
        Thread t2 = new Thread(new ChangeAgeTask(originUser), "T2");
        t1.start();
        t1.join();
        t2.start();
        t2.join();

        System.err.println("Result: " + reference.get());

    }

    private static class ChangeAgeTask implements Runnable{
        private User user;

        public ChangeAgeTask(User user) {
            this.user = user;
        }

        @Override
        public void run() {
            User origin = reference.get();
            User newUser = new User();
            newUser.setName(origin.getName());
            newUser.setAge(origin.age + 1);

            //使用reference获取到的最新的变量,可以修改成功
            System.err.println(Thread.currentThread().getName() + "——修改年龄,结果: " + reference.compareAndSet(origin,newUser));

            //由于采用了CAS算法,每次修改都会比较expect值,因此使用原始的变量作为expect时,算法检测到变量已修改,则操作失败
//            System.err.println(Thread.currentThread().getName() + "——修改年龄,结果: " + reference.compareAndSet(user,newUser));
        }
    }
}
