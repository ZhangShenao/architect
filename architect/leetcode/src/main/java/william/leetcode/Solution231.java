package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/15 16:34
 * @Description:https://leetcode.com/problems/power-of-two/
 */
public class Solution231 {
    public static void main(String[] args) {
        Solution231 s = new Solution231();
        System.err.println(s.isPowerOfTwo(218));
    }

    public boolean isPowerOfTwo(int n) {
        if (n <= 0){
            return false;
        }
        if (n == 1){
            return true;
        }
        return n % 2 == 0 && isPowerOfTwo(n / 2);
    }

}
