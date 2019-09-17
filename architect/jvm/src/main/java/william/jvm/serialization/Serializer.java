package william.jvm.serialization;

/**
 * @Author zhangshenao
 * @Date 2019-09-17
 * @Description 序列化接口
 * 序列化的意义：将内存中的对象实体转换成字节数组,用于网络传输或者持久化
 */
public interface Serializer<T> {
    byte[] serialize(T obj) throws Exception;

    T deserialize(byte[] data, Class<T> clazz) throws Exception;

    void persist(T obj, String path) throws Exception;

    T load(String path) throws Exception;
}
