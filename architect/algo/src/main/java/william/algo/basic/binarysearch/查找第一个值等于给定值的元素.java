package william.algo.basic.binarysearch;

/**
 * @Author zhangshenao
 * @Date 2021-11-14
 * @Description
 */
public class 查找第一个值等于给定值的元素 {
    public int search(int[] nums, int target) {
        //边界
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] > target) {
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else { //找到了nums[mid]=target,需要判断是否为第一个
                if (mid == 0 || nums[mid - 1] != target) {   //nums[mid]就是第一个=target的元素
                    return mid;
                }
                //否则继续向前找
                high = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        查找第一个值等于给定值的元素 s = new 查找第一个值等于给定值的元素();
        int[] nums = new int[] {1, 3, 4, 5, 6, 8, 8, 8, 11, 18};
        int target = 8;
        System.err.println(s.search(nums, target));  //第一个等于8的元素,应该返回下标5
    }
}
