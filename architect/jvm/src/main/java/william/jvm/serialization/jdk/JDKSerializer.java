package william.jvm.serialization.jdk;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import william.jvm.serialization.Serializer;

/**
 * @Author zhangshenao
 * @Date 2019-09-17
 * @Description 基于JDK原生的序列化机制
 */
public class JDKSerializer<T> implements Serializer<T> {
    @Override
    public byte[] serialize(T obj) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(obj);
        return out.toByteArray();
    }

    @Override
    public T deserialize(byte[] data, Class<T> clazz) throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(in);
        return (T) ois.readObject();
    }

    @Override
    public void persist(T obj, String path) throws Exception {
        FileOutputStream out = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(obj);
    }

    @Override
    public T load(String path) throws Exception {
        FileInputStream in = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(in);
        return (T) ois.readObject();
    }
}
