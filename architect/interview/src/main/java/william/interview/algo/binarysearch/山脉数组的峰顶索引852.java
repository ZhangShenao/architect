package william.interview.algo.binarysearch;

/**
 * https://leetcode-cn.com/problems/peak-index-in-a-mountain-array/description/
 */
public class 山脉数组的峰顶索引852 {
    public int peakIndexInMountainArray(int[] arr) {
        //边界条件
        if (arr == null || arr.length < 3) {
            return -1;
        }


        //采用二分的思想,如果peak存在,那么一定能够保证peak左侧的元素是递增的、peak的右边是递减的
        //因此要找的peak是第一个破坏了数组递增性的元素
        int l = 1;
        int r = arr.length - 2;
        int result = -1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid + 1] > arr[mid]) {   //[mid,mid+1]仍然保持递增性,说明peak在右边,丢弃左边部分
                l = mid + 1;
            } else { //从mid开始数组已经递减,说明peak在左边,丢弃右边部分
                //记录result
                result = mid;
                r = mid - 1;
            }
        }

        return result;
    }
}
