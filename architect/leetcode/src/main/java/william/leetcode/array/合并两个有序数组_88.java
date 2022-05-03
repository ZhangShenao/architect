package william.leetcode.array;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/3 上午10:39
 * <p>
 * https://leetcode-cn.com/problems/merge-sorted-array/
 */
public class 合并两个有序数组_88 {
    //方法一:在原nums1数组上倒序遍历,插入两个数组中的较大元素
    //时间复杂度O(m+n)
    //空间复杂度O(1)
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //边界条件
        if (nums2 == null || nums2.length == 0 || n == 0) {
            return;
        }
        //倒序遍历两个数组,在末尾插入最大的元素,避免将nums1中的元素覆盖
        int k = m + n;

        for (int i = k - 1, l = m - 1, r = n - 1; i >= 0; i--) {
            if (r < 0) { //如果nums2已经遍历完成,因为nums1已经有序,直接返回即可
                return;
            }

            //如果nums1已经遍历完成,则直接插入nums2的元素
            if (l < 0) {
                nums1[i] = nums2[r--];
                continue;
            }

            //num1和num2都有剩余元素,则插入两个数组中最大的元素
            if (nums2[r] >= nums1[l]) {
                nums1[i] = nums2[r--];
            } else {
                nums1[i] = nums1[l--];
            }
        }
    }

    //方法二:创建临时数组,遍历两个数组,依次将较小的元素放入临时数组,然后将临时数组拷贝回nums1
    //时间复杂度O(m+n)
    //空间复杂度O(m+n)
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        //边界条件
        if (nums2 == null || nums2.length == 0 || n == 0) {
            return;
        }
        //创建临时数组
        int k = m + n;
        int[] tmp = new int[k];

        //遍历两个数组,将较小的元素放入临时数组
        for (int i = 0, l = 0, r = 0; i < k; i++) {
            if (l >= m) {    //nums1已经遍历完成,则直接使用nums2中的元素
                tmp[i] = nums2[r++];
            } else if (r >= n) {  //nums2已经遍历完成,则直接使用nums1中的元素
                tmp[i] = nums1[l++];
            } else if (nums1[l] <= nums2[r]) {    //两个数组均有剩余元素,则使用较小的元素
                tmp[i] = nums1[l++];
            } else {
                tmp[i] = nums2[r++];
            }
        }

        //将临时数组拷贝回nums1
        for (int i = 0; i < k; i++) {
            nums1[i] = tmp[i];
        }
        tmp = null;
    }
}
