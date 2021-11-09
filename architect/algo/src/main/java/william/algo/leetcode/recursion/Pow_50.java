package william.algo.leetcode.recursion;

/**
 * @Author zhangshenao
 * @Date 2021-11-05
 * @Description https://leetcode-cn.com/problems/powx-n/
 * 计算x的n次方
 * 方法一:循环n次,每次乘X 时间复杂度O(n)
 * 方法二:递归 每次计算n/2次方 时间复杂度O(logn)
 */
public class Pow_50 {
    public double myPow(double x, int n) {
        //边界
        if (x == 0D) {
            return 0;
        }

        if (n == 0) {
            return 1;
        }

        if (n > 0) {
            return powRecursive(x, n);
        }

        return 1D / powRecursive(x, -n);
    }

    //递归计算x的n次方,这里的n定义为非负整数
    private double powRecursive(double x, int n) {
        //递归退出条件
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }

        //如果n为偶数,则递归计算n/2次方
        if (n % 2 == 0) {
            return powRecursive(x, n / 2) * powRecursive(x, n / 2);
        }

        //如果n为奇数
        return x * powRecursive(x, n / 2) * powRecursive(x, n / 2);
    }
}
