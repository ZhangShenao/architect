package william.algo.basic.binarysearch;

/**
 * @Author zhangshenao
 * @Date 2021-11-14
 * @Description 普通二分查找算法
 */
public class BinarySearch {
    public int binarySearch(int[] nums, int target) {
        //边界
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;   //防止溢出

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        int[] nums = new int[] {1, 9, 15, 21, 50, 100, 205, 9000, 10008};
        System.err.println(bs.binarySearch(nums, 21));
        System.err.println(bs.binarySearch(nums, 10008));
        System.err.println(bs.binarySearch(nums, 10009));
    }
}
