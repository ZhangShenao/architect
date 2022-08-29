package william.zookeeper.serialization;

import lombok.Data;
import lombok.ToString;
import org.apache.jute.InputArchive;
import org.apache.jute.OutputArchive;
import org.apache.jute.Record;

import java.io.IOException;

/**
 * @Auther: ZhangShenao
 * @Date 2022/8/29 11:32 AM
 * @Description:
 */
@Data
@ToString
public class User implements Record {
    private long id;
    private String name;

    //序列化
    @Override
    public void serialize(OutputArchive outputArchive, String tag) throws IOException {
        outputArchive.startRecord(this, tag);
        outputArchive.writeLong(id, "id");
        outputArchive.writeString(name, "name");
        outputArchive.endRecord(this, tag);
    }

    //反序列化
    @Override
    public void deserialize(InputArchive inputArchive, String tag) throws IOException {
        inputArchive.startRecord(tag);
        this.id = inputArchive.readLong("id");
        this.name = inputArchive.readString("name");
        inputArchive.endRecord(tag);
    }
}
