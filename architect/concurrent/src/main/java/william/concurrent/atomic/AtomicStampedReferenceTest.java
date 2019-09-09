package william.concurrent.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/20 16:45
 * @Description:对CAS操作引入版本号，解决ABA问题
 */
public class AtomicStampedReferenceTest {
    public static void main(String[] args) {
        User user = new User();
        user.setName("James");
        user.setAge(34);
        User newUser = new User();
        newUser.setName("Kobe");
        newUser.setAge(39);
        AtomicStampedReference reference = new AtomicStampedReference(user, 0);
        Object expectedValue = reference.get(new int[5]);
        int expectedStamp = reference.getStamp();

        while (!reference.compareAndSet(expectedValue,newUser,expectedStamp,expectedStamp + 1)){
            expectedValue = reference.get(new int[5]);
            expectedStamp = reference.getStamp();
        }
        System.err.println(expectedValue);
    }
}
