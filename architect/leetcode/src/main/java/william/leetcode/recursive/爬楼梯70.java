package william.leetcode.recursive;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/2 下午3:14
 * <p>
 * https://leetcode-cn.com/problems/climbing-stairs/
 */
public class 爬楼梯70 {
    //使用一个map保存中间计算结果,避免重复计算的问题
    private Map<Integer, Integer> cache = new HashMap<>();

    //采用递归实现
    //爬n层楼梯的方法数量=先爬1层后爬n-1层的方法数量+先爬2层再爬n-2层的方法数量
    //递推公式:f(n)=f(n-1)+f(n-2)
    public int climbStairs(int n) {
        //边界条件
        if (n <= 0) {
            return 0;
        }

        //递归终止条件
        if (n == 1) {    //1层楼梯有1种爬法
            return 1;
        }
        if (n == 2) {    //2层楼梯有2种爬法
            return 2;
        }

        if (cache.containsKey(n)) { //借助map查询中间结果
            return cache.get(n);
        }

        int result = climbStairs(n - 1) + climbStairs(n - 2);
        cache.put(n, result);   //保存中间结果
        return result;
    }
}
