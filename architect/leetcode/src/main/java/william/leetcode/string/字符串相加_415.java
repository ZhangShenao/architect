package william.leetcode.string;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/17 上午10:17
 * <p>
 * https://leetcode.cn/problems/add-strings/
 */
public class 字符串相加_415 {
    //倒序遍历两个字符串,依次从最低位开始相加,并记录进位
    //长度较短的数字在前面补零
    //时间复杂度O(N)
    //空间复杂度O(1)
    public String addStrings(String num1, String num2) {
        //边界条件
        if (num1 == null || num1.length() == 0) {
            return num2;
        }
        if (num2 == null || num2.length() == 0) {
            return num1;
        }

        int carry = 0;  //记录进位

        StringBuilder result = new StringBuilder(); //保存结果

        //从最低位(倒序)开始遍历两个字符串
        for (int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0 || carry > 0; i--, j--) {   //需注意字符串遍历完成但是进位不为零的情况
            int n1 = i >= 0 ? (num1.charAt(i) - '0') : 0; //长度不足在前面补零
            int n2 = j >= 0 ? (num2.charAt(j) - '0') : 0;   //长度不足在前面补零

            if (n1 < 0 || n2 < 0) { //边界条件:非法字符校验
                throw new IllegalArgumentException();
            }

            //计算加和,需要考虑前一位的进位
            int sum = n1 + n2 + carry;

            //保存当前位结果
            int n = sum % 10;
            result.insert(0, n);

            //记录进位
            carry = sum / 10;
        }

        //返回结果字符串
        return result.toString();
    }
}
