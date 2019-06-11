package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/11 10:45
 * @Description:https://leetcode.com/problems/monotonic-array/
 */
public class Solution896 {
    public static void main(String[] args) {
        int[] A = {1,1,1,1};
        System.err.println(isMonotonic(A));
    }

    public static boolean isMonotonic(int[] A) {
        if (A == null || A.length < 1) {
            return false;
        }
        boolean asc = true;

        //确定是升序还是降序
        int i = 0;
        for (;i < A.length - 1;){
            if (A[i] > A[i + 1]){
                asc = false;
                break;
            }else if (A[i] < A[i + 1]){
                break;
            }else {
                ++i;
            }
        }

        //全相等的情况
        if (i >= A.length - 1){
            return true;
        }

        i = 0;
        for (;i < A.length - 1;){
            if (A[i] > A[i + 1] && asc){
                return false;
            }
            if (A[i] < A[i + 1] && !asc){
                return false;
            }
            ++i;
        }
        return true;
    }


}
