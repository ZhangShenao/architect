package william.leetcode.array;

/**
 * @Author zhangshenao
 * @Date 2021-09-26
 * @Description 合并两个有序数组
 * https://leetcode-cn.com/problems/merge-sorted-array/
 */
public class Solution88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //边界条件：判空，这里先省略
        int[] result = new int[m + n];
        int r = 0;
        int i = 0;
        int j = 0;

        //执行merge操作
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                result[r] = nums1[i];
                r++;
                i++;
            } else {
                result[r] = nums2[j];
                r++;
                j++;
            }
        }

        //处理其中一个数组还未完全遍历完的情况
        while (i < m) {
            result[r] = nums1[i];
            r++;
            i++;
        }
        while (j < n) {
            result[r] = nums2[j];
            r++;
            j++;
        }

        //写回原数组
        for (int k = 0; k < r; k++) {
            nums1[k] = result[k];
        }
    }


}
