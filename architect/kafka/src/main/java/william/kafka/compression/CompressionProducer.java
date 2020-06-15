package william.kafka.compression;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @Author zhangshenao
 * @Date 2020-06-07
 * @Description 开启压缩的Producer
 */
public class CompressionProducer {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

    public static void main(String[] args) {
        /*Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        //开启消息压缩,压缩算法为GZIP
        //压缩可以有效地节省网络带宽,是一种典型的时间换空间的思想(trade-off)
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "gzip");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        ProducerRecord<String, String> record =
                new ProducerRecord<>(KafkaConstants.TOPIC_NAME, "Compression Key", "Compression Value");
        producer.send(record, (metadata, exception) -> System.err
                .println("partition: " + metadata.partition() + ", offset: " + metadata.offset()));
        System.err.println("消息发送成功");
        producer.close();*/

        Instant instant = Instant.ofEpochMilli(1591775613444L);
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        String time = FORMATTER.format(localDateTime);
        System.err.println(time);
    }
}
