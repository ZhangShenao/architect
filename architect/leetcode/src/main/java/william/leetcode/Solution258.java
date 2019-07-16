package william.leetcode;


/**
 * @Author: ZhangShenao
 * @Date: 2019/7/16 10:48
 * @Description:https://leetcode.com/problems/add-digits/
 */
public class Solution258 {
    public static void main(String[] args) {
        Solution258 s = new Solution258();
        System.err.println(s.addDigitsSimple(38));
    }

    public int addDigits(int num) {
        if (num < 10) {
            return num;
        }
        int sum = 0;

        //拿到各个位的数字,都加起来
        while (num > 0) {
            sum += (num % 10);
            num = num / 10;
        }

        //递归
        return addDigits(sum);
    }

    public int addDigitsSimple(int num) {
        return 1 + (num - 1) % 9;
    }
}
