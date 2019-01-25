package william.concurrent.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/20 16:26
 * @Description:
 */
public class AtomicIntegerFieldUpdaterTest {
    public static void main(String[] args) {
        User user = new User();
        user.setName("James");
        user.setAge(34);
        //需要被AtomicIntegerFieldUpdater修改的字段，volatile修饰
        AtomicIntegerFieldUpdater<User> updater = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");
        updater.getAndIncrement(user);
        System.err.println("New Age: " + updater.get(user));
        System.err.println("Origin Age: " + user.getAge());

    }
}
