package william.leetcode.bit;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/16 上午9:17
 * https://leetcode.cn/problems/counting-bits/
 */
public class 比特位计数_338 {
    //借助奇偶性进行计算
    //如果n为奇数,则n对应二进制中1的数量,等于它前一个偶数对应二进制中1的数量+1
    //如果n为偶数,则n对应二进制中1的数量,等于n/2对应二进制中1的数量
    //时间复杂度O(N)
    //空间复杂度O(N)
    public int[] countBits(int n) {
        int[] result = new int[n + 1];  //结果数组
        result[0] = 0;  //初始化0的奇数

        //遍历数组,根据进行奇偶性进行计算
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) { //如果n为偶数,则n对应二进制中1的数量,等于n/2对应二进制中1的数量
                result[i] = result[i >> 1];
            } else {    //如果n为奇数,则n对应二进制中1的数量,等于它前一个偶数对应二进制中1的数量+1
                result[i] = result[i - 1] + 1;
            }
        }

        return result;
    }
}
