package william.interview.collection.iterable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机字符串生成器
 * 实现Iterable接口
 */
public class RandomStringIterator implements Iterable<String> {
    private List<String> candidates;

    public RandomStringIterator(List<String> candidates) {
        this.candidates = candidates;
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public String next() {
                return candidates.get(ThreadLocalRandom.current().nextInt(candidates.size()));
            }
        };
    }

    public static void main(String[] args) {
        List<String> candidates = Arrays.asList("go","java","python","c","cpp");
        RandomStringIterator ite = new RandomStringIterator(candidates);
        int idx = 0;

        for (String s : ite){
            System.out.println(s);
            if (++idx == 100){
                break;
            }
        }
    }

}
