package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/17 13:18
 * @Description:https://leetcode.com/problems/power-of-three/
 */
public class Solution326 {
    public boolean isPowerOfThree(int n) {
        if (n < 1) {
            return false;
        }
        while (n > 1) {
            if (n % 3 != 0) {
                return false;
            }
            n /= 3;
        }
        return (n == 1);
    }
}
