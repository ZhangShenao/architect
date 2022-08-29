package william.zookeeper.serialization;

import org.apache.jute.BinaryInputArchive;
import org.apache.jute.BinaryOutputArchive;
import org.apache.zookeeper.server.ByteBufferInputStream;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

/**
 * @Auther: ZhangShenao
 * @Date 2022/8/29 11:31 AM
 * @Description: 序列化
 * Zookeeper采用内置的Apache的Jute框架进行序列化/反序列化
 */
public class TestSerialization {
    private static final String TAG = "user";   //序列化Tag
    
    public static void main(String[] args) throws Exception {
        //序列化
        User user = new User();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BinaryOutputArchive outputArchive = BinaryOutputArchive.getArchive(outputStream);
        user.setId(1L);
        user.setName("James");
        user.serialize(outputArchive, TAG);
        ByteBuffer byteBuffer = ByteBuffer.wrap(outputStream.toByteArray());


        //反序列化
        ByteBufferInputStream inputStream = new ByteBufferInputStream(byteBuffer);
        BinaryInputArchive inputArchive = BinaryInputArchive.getArchive(inputStream);
        User serialized = new User();
        serialized.deserialize(inputArchive, TAG);
        System.out.println("Deserialize Result: " + serialized);


        //释放资源
        inputStream.close();
        outputStream.close();
    }
}
