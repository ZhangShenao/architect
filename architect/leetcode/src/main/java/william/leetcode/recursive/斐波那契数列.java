package william.leetcode.recursive;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/2 下午3:21
 * <p>
 * https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/
 */
public class 斐波那契数列 {
    private final int MOD = 1000000007; //结果取模

    //使用map保存中间结果,避免重复计算的问题
    private Map<Integer, Integer> cache = new HashMap<>();

    //使用递归实现
    //递推公式:F(N) = F(N - 1) + F(N - 2)
    public int fib(int n) {
        //边界条件
        if (n < 0) {
            throw new IllegalArgumentException();
        }

        //递归终止条件
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (cache.containsKey(n)) {  //先查询中间结果
            return cache.get(n);
        }

        int result = (fib(n - 1) + fib(n - 2)) % MOD;
        cache.put(n, result);    //保存中间结果

        return result;

    }
}
