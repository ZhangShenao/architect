package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/12 16:48
 * @Description:https://leetcode.com/problems/factorial-trailing-zeroes/
 */
public class Solution172 {
    public static void main(String[] args) {
        Solution172 s = new Solution172();
        System.err.println(s.trailingZeroes(13));
    }

    public int trailingZeroes(int n) {
        //因为0是由2*5产生的,且2的数量一定>=5,因此统计出有几个5也就是有几个0
        return (n < 5) ? 0 : n / 5 + trailingZeroes(n / 5);
    }
}
