package william.algo.basic.binarysearch;

/**
 * @Author zhangshenao
 * @Date 2021-11-14
 * @Description
 */
public class 查找最后一个小于等于给定值的元素 {
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
            } else {    //找到了nums[mid] >= target
                if (mid == nums.length - 1 || nums[mid + 1] > target) {  //mid就是最后一个<=target的下标
                    return mid;
                }
                low = mid + 1;  //否则继续往后找
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        查找最后一个小于等于给定值的元素 s = new 查找最后一个小于等于给定值的元素();
        int[] nums = new int[] {3, 5, 6, 8, 9, 10};
        int target = 7;
        System.err.println(s.search(nums, target)); //最后一个小于等于7的元素是6,应该返回下标=2
    }
}
