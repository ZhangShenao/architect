package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/11 10:28
 * @Description:https://leetcode.com/problems/valid-mountain-array/
 */
public class Solution941 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        System.err.println(validMountainArray(arr));
    }

    public static boolean validMountainArray(int[] A) {
        if (A == null || A.length < 3) {
            return false;
        }

        //找到最大索引
        int max = 0;
        int i = 0;
        while (i < A.length - 1) {
            int a1 = A[i];
            int a2 = A[i + 1];
            if (a1 == a2) {
                return false;
            }
            if (a1 > a2) {
                break;
            }
            ++i;
        }

        max = i;
        //判断后续索引
        while (i < A.length - 1) {
            if (A[i] <= A[i + 1]) {
                return false;
            }
            ++i;
        }
        return (max > 0 && max < A.length - 1);
    }
}
