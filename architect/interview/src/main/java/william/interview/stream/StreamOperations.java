package william.interview.stream;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Java Stream流操作
 * 流本质上是随着时间产生的数据序列
 */
public class StreamOperations {
    @Test
    public void testMapAndFilter(){
        List<Integer> nums = Stream.of(1, 2, 3, 4, 5)
                .map(x -> x * x)
                .filter(x -> x < 20)
                .collect(Collectors.toList());
        System.out.println(nums);
    }
}
