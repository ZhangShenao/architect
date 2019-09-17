package william.jvm.serialization;

import org.junit.Before;
import org.junit.Test;

import william.jvm.serialization.jdk.JDKSerializer;

/**
 * @Author zhangshenao
 * @Date 2019-09-17
 * @Description
 */
public class TestSerialization {
    private Serializer<User> serializer;

    @Before
    public void init() {
        serializer = new JDKSerializer<>();
    }

    @Test
    public void testPersist() throws Exception {
        User user = new User();
        user.setName("James");
        user.setAge(34);
        String path = "User.txt";
        serializer.persist(user, path);
    }

    @Test
    public void testLoad() throws Exception {
        User user = serializer.load("User.txt");
        System.err.println(user);
    }
}
