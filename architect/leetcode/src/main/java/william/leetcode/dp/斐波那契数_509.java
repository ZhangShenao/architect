package william.leetcode.dp;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/18 上午10:30
 * <p>
 * https://leetcode.cn/problems/fibonacci-number/
 */
public class 斐波那契数_509 {
    //采用动态规划(Dynamic Programming)思想
    //创建dp table,其中dp[i]就是fib(i)的值
    //状态转移公式:william.leetcode.dp[i]=william.leetcode.dp[i-1]+william.leetcode.dp[i-2]
    //时间复杂度O(N) 需要遍历dp table
    //空间复杂度O(N) 用于保存dp table
    public int fib(int n) {
        //边界条件
        if (n <= 1) {
            return n;
        }

        //创建dp table
        int[] dp = new int[n + 1];

        //初始化db table
        dp[0] = 0;
        dp[1] = 1;

        //遍历dp table,根据状态转移公式进行计算
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];  //状态转移公式
        }

        //返回dp[n]
        return dp[n];
    }
}
