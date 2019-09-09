package william.leetcode;


/**
 * @Author: ZhangShenao
 * @Date: 2019/6/26 13:38
 * @Description:https://leetcode.com/problems/add-binary/
 */
public class Solution67 {
    public static void main(String[] args) {
        Solution67 s = new Solution67();
        System.err.println(s.addBinary("11","1"));
    }

    public String addBinary(String a, String b) {
        StringBuilder builder = new StringBuilder();

        //记录两个下标
        int i = a.length() - 1;
        int j = b.length() - 1;

        //记录进位
        int carry = 0;

        while (i >= 0 || j >= 0 || carry >= 1){
            //记录当前的和
            int sum = 0;
            if (i >= 0){
                sum += Integer.parseInt("" + a.charAt(i));
                --i;
            }
            if (j >= 0){
                sum += Integer.parseInt("" + b.charAt(j));
                --j;
            }
            sum += carry;

            carry = sum / 2;
            sum = sum % 2;

            builder.insert(0,sum);
        }
        return builder.toString();
    }
}
