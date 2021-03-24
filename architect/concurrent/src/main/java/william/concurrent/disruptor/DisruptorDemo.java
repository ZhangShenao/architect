package william.concurrent.disruptor;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import lombok.Data;
import lombok.ToString;

/**
 * @Author zhangshenao
 * @Date 2021-03-24
 * @Description Disruptor使用示例
 * Disruptor是一款高性能的有界内存队列
 */
public class DisruptorDemo {
    public static void main(String[] args) throws InterruptedException {
        //指定RingBuffer大小,必须是2的N次方
        int ringBufferSize = 1024;

        //构建Disruptor实例
        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, ringBufferSize, DaemonThreadFactory.INSTANCE);

        //注册事件处理器
        disruptor.handleEventsWith((event, sequence, endOfBatch) -> System.err.println("Handle Event: " + event));

        //启动Disruptor
        disruptor.start();

        //获取RingBuffer
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        //生产Event
        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) {
            bb.putLong(0, l);
            // 生产者生产消息
            ringBuffer.publishEvent((event, sequence, buffer) -> event.setValue(buffer.getLong(0)), bb);
            Thread.sleep(1000);
        }

    }

    //自定义Event
    //在Disruptor中，生产者生产的和消费者消费的对象叫做Event
    @Data
    @ToString
    private static class LongEvent {
        private long value;
    }
}
