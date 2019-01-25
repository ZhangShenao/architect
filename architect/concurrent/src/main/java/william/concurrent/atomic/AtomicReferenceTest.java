package william.concurrent.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/20 16:20
 * @Description:
 */
public class AtomicReferenceTest {
    public static void main(String[] args) {
        User originUser = new User();
        originUser.setName("Kobe");
        originUser.setAge(38);
        User newUser = new User();
        newUser.setName("James");
        newUser.setAge(34);
        AtomicReference<User> userAtomicReference = new AtomicReference<>(originUser);
        userAtomicReference.compareAndSet(originUser, newUser);
        System.err.println("NewUser: " + userAtomicReference.get());
        System.err.println("Origin User: " + originUser);
    }


}
