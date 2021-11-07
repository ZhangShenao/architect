package william.algo.binarysearch;

/**
 * @Author zhangshenao
 * @Date 2021-11-07
 * @Description https://leetcode-cn.com/problems/valid-perfect-square/?utm_source=LCUS&utm_medium=ip_redirect
 * &utm_campaign=transfer2china
 * 基于二分查找的思想,时间复杂度O(logn)
 */
public class 有效的完全平方数_367 {
    public boolean isPerfectSquare(int num) {
        //边界
        if (num <= 0) {
            throw new IllegalArgumentException();
        }

        int min = 0;
        int max = num;

        while (min <= max) {
            int mid = min + (max - min) / 2;
            int res = num / mid;
            if (res == mid) {
                if (num % mid == 0) {    //整除直接返回
                    return true;
                }
                min = mid + 1;  //未整除,说明小数部分被舍弃了,继续处理
            } else if (res > mid) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }

        return false;

    }
}
