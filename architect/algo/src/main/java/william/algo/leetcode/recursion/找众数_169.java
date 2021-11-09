package william.algo.leetcode.recursion;

/**
 * @Author zhangshenao
 * @Date 2021-11-05
 * @Description https://leetcode-cn.com/problems/majority-element/
 * 方法一：遍历两层数组，第一层遍历分别取每一个元素，第二层遍历计算该元素出现的次数 时间复杂度O(n*n) 空间复杂度O(1)
 * 方法二：借助一个Map，保存每个元素及其出现的次数 时间复杂度O(n) 空间复杂度O(n)
 * 方法三：先对数组进行排序,然后遍历有序数组,依次计算每个元素出现的次数 时间复杂度O(n*logn) 空间复杂度O(1)
 */
public class 找众数_169 {
    public int majorityElement(int[] nums) {
        //边界
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }

        //对数组进行排序
        quickSort(nums, 0, nums.length - 1);

        //遍历有序数组,找到众数
        int count = 0;
        int prev = nums[0];

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == prev) {   //累积计数
                ++count;
                if (count > nums.length / 2) {  //找到了,直接返回
                    return nums[i];
                }
            } else {    //重置计数
                prev = nums[i];
                count = 1;
            }

        }

        //未找到众数，理论上这种情况不会出现
        throw new IllegalArgumentException();
    }


    //对数组nums的[start,end]区间进行快速排序,时间复杂度O(n*logn)
    private void quickSort(int[] nums, int start, int end) {
        //递归退出条件
        if (start >= end) {
            return;
        }

        int partition = partition(nums, start, end);
        quickSort(nums, start, partition - 1);
        quickSort(nums, partition + 1, end);
    }

    //双路指针定位partition
    private int partition(int[] nums, int start, int end) {
        //默认选取首个元素作为基准值
        int pivot = nums[start];
        int left = start + 1;
        int right = end;

        while (true) {
            //寻找左边第一个大于基准值的元素,和右边第一个小于基准值的元素,进行交换
            while (left <= end && nums[left] < pivot) {
                ++left;
            }
            while (right >= start && nums[right] > pivot) {
                --right;
            }
            if (left > right) {
                break;
            }

            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            ++left;
            --right;
        }

        //right为左边最后一个小于基准值的元素,与基准值进行交换
        int tmp = nums[start];
        nums[start] = nums[right];
        nums[right] = tmp;
        return right;
    }

    public static void main(String[] args) {
        找众数_169 test = new 找众数_169();
        int[] nums = new int[] {8, 9, 8, 9, 8};
        System.err.println(test.majorityElement(nums));
    }


}
