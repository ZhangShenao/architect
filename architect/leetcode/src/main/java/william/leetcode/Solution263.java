package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/17 11:50
 * @Description:https://leetcode.com/problems/ugly-number/
 */
public class Solution263 {
    public boolean isUgly(int num) {
        if (num < 1){
            return false;
        }
        if (num <= 2){
            return true;
        }

        while (num > 2){
            if (num % 5 == 0){
                num /= 5;
            }else if (num % 3 == 0){
                num /= 3;
            }else if (num % 2 == 0){
                num /= 2;
            }else {
                return false;
            }
        }
        return true;

    }
}
