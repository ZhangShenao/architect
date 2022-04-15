package william.interview.buffer;

import org.junit.Test;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * 使用Fork-Join模式进行词频统计
 */
public class WordCount {
    //单线程词频统计
    @Test
    public void testCountBySingleThread() {
        String fileName = "word.txt";
        File file = new File(fileName);
        long startTime = System.currentTimeMillis();
        try (FileChannel channel = new RandomAccessFile(fileName, "r").getChannel()) {
            MappedByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());

            //将文件内容直接转换成字符串
            String data = StandardCharsets.US_ASCII.newDecoder().decode(buf).toString();
            Map<String, Long> result = wordCountFromString(data);
            System.out.println("count size: " + result.size());
            System.out.println("count of aabbc: " + result.get("aabbc"));
            System.out.println("costs: " + (System.currentTimeMillis() - startTime));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //基于Fork-Join Pool多线程词频统计
    @Test
    public void testCountByForkJoinPool() throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        String fileName = "word.txt";
        File file = new File(fileName);
        long trunkSize = 1024 * 1024 * 8;   //分片大小
        long total = file.length();
        long pos = 0;

        Map<String, Long> result = new HashMap<>();
        List<Future<Map<String, Long>>> futures = new ArrayList<>();

        long startTime = System.currentTimeMillis();
        //Fork
        while (pos < total) {
            long next = Math.min(pos + trunkSize, total);
            WordCountTask task = new WordCountTask(fileName, pos, next);
            futures.add(pool.submit(task)); //保存Future
            pos = next;
        }

        //Join
        for (Future<Map<String, Long>> f : futures) {
            Map<String, Long> partition = f.get();
            for (Map.Entry<String, Long> e : partition.entrySet()) {
                incrWordCount(result, e.getKey(), e.getValue());
            }
        }

        System.out.println("count size: " + result.size());
        System.out.println("count of aabbc: " + result.get("aabbc"));
        System.out.println("costs: " + (System.currentTimeMillis() - startTime));

    }

    private class WordCountTask implements Callable<Map<String, Long>> {
        private final String fileName;
        private final long start;
        private final long end;

        public WordCountTask(String fileName, long start, long end) {
            this.fileName = fileName;
            this.start = start;
            this.end = end;
        }

        @Override
        public Map<String, Long> call() {
            if (end < start) {  //边界条件判断
                return Collections.emptyMap();
            }

            //使用Channel Map将文件内容直接映射到内存
            try (FileChannel channel = new RandomAccessFile(fileName, "r").getChannel()) {
                //默认情况: Device -> Kernel Space -> UserSpace(buffer) -> Thread
                //map:
                MappedByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, start, (end - start));

                //将文件内容直接转换成字符串
                String data = StandardCharsets.US_ASCII.newDecoder().decode(buf).toString();
                return wordCountFromString(data);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    //统计字符串中的词频
    private Map<String, Long> wordCountFromString(String s) {
        Map<String, Long> result = new HashMap<>();

        //创建StringTokenizer默认字符串分词器
        StringTokenizer tokenizer = new StringTokenizer(s);
        while (tokenizer.hasMoreTokens()) {
            String word = tokenizer.nextToken();
            incrWordCount(result, word, 1);
        }
        return result;
    }

    private void incrWordCount(Map<String, Long> current, String word, long incr) {
        if (current.containsKey(word)) {
            current.put(word, current.get(word) + incr);
        } else {
            current.put(word, incr);
        }
    }
}
