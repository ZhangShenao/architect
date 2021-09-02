package william.algo.stack;

/**
 * @Author zhangshenao
 * @Date 2021-09-02
 * @Description
 */
public class 有效的括号 {
    //判断一个字符串是否是有效的括号
    //假设字符串中只包含'('和')'
    //可以记录左括号的数量来代替入栈操作,降低空间复杂度
    //时间复杂度O(n) 空间复杂度O(1)
    public static boolean isValid(String str) {
        //边界条件——空串
        if (str == null || str.length() <= 0) {
            return false;
        }

        //边界条件——奇数长度的字符串,一定是非法的
        if (str.length() % 2 == 1) {
            return false;
        }

        //记录左括号的数量
        int leftBraceNum = 0;

        for (int i = 0; i < str.length(); i++) {
            //遍历字符串
            char c = str.charAt(i);

            if ('(' == c) {
                //如果当前字符为左括号,则将计数器+1
                ++leftBraceNum;
            } else if (')' == c) {
                //如果当前字符为右括号,则判断左括号数量
                if (leftBraceNum <= 0) {
                    //如果左括号数量<0,说明有多出来的右括号,则为非法字符串
                    return false;
                }

                //如果左括号数量>0,则消除一对括号,计数器减一
                --leftBraceNum;
            }
        }

        //最后判断是否左右括号都已经匹配成功
        return (leftBraceNum == 0);
    }

    public static void main(String[] args) {
        String str1 = "()()()()";
        System.err.println(str1 + ": is valid: " + isValid(str1));

        String str2 = "()()()(";
        System.err.println(str2 + ": is valid: " + isValid(str2));

        String str3 = "((()))";
        System.err.println(str3 + ": is valid: " + isValid(str3));

        String str4 = "((())";
        System.err.println(str4 + ": is valid: " + isValid(str4));

        String str5 = "((()))(";
        System.err.println(str5 + ": is valid: " + isValid(str5));
    }
}
