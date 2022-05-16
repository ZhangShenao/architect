package william.leetcode.bit;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/16 上午9:25
 * <p>
 * https://leetcode.cn/problems/hamming-distance/
 */
public class 汉明距离_461 {
    //首先对x,y进行按位异或操作
    //然后判断异或结果对应的二进制中包含的1的数量
    //特性:N & (N-1)可以消除N中最低位的1
    //根据这个特性可以计算异或结果中1的数量
    public int hammingDistance(int x, int y) {
        int distance = 0;

        //对x和y进行按位异或,然后计算xor中1的数量
        for (int xor = x ^ y; xor > 0; xor &= xor - 1) {
            //特性:N & (N-1)可以消除N中最低位的1
            ++distance;
        }

        return distance;
    }
}
