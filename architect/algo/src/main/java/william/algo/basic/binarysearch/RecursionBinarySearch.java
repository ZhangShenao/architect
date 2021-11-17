package william.algo.basic.binarysearch;

/**
 * @Author zhangshenao
 * @Date 2021-11-14
 * @Description 递归二分查找
 */
public class RecursionBinarySearch {
    public int binarySearch(int[] nums, int target) {
        //边界
        if (nums == null || nums.length == 0) {
            return -1;
        }

        return binarySearchRecursion(nums, 0, nums.length - 1, target);
    }


    //递归函数:在nums的[low,high]区间内查找target
    private int binarySearchRecursion(int[] nums, int low, int high, int target) {
        //递归终止条件
        if (low > high) {
            return -1;
        }

        int mid = low + (high - low) / 2;   //防止溢出
        if (nums[mid] == target) {
            return mid;
        }

        if (nums[mid] > target) {
            return binarySearchRecursion(nums, low, mid - 1, target);
        }
        return binarySearchRecursion(nums, mid + 1, high, target);
    }

    public static void main(String[] args) {
        RecursionBinarySearch bs = new RecursionBinarySearch();
        int[] nums = new int[] {1, 9, 15, 21, 50, 100, 205, 9000, 10008};
        System.err.println(bs.binarySearch(nums, 21));
        System.err.println(bs.binarySearch(nums, 10008));
        System.err.println(bs.binarySearch(nums, 10009));
    }
}
