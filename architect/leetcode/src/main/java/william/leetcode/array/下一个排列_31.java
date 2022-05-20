package william.leetcode.array;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/20 上午11:34
 * <p>
 * https://leetcode.cn/problems/next-permutation/
 */
public class 下一个排列_31 {
    //遍历数组
    //首先找到右边第一个较小数nums[i],满足nums[i] < num[i + 1]
    //然后找到右边第一个较大数nums[j],满足nums[j] > nums[i]
    //然后交换i和j两个元素,此时i后面的元素为降序
    //最后将i后面的元素反转为升序
    //时间复杂度O(N) 最多需要遍历两次数组,还有一次反转操作
    //空间复杂度O(1)
    public void nextPermutation(int[] nums) {
        //边界条件
        if (nums == null || nums.length < 2) {
            return;
        }

        //从倒数第二个元素开始向前遍历,找到第一个较小数nums[i],满足nums[i] < num[i + 1]
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            --i;
        }

        //从倒数第一个元素开始,找到第一个较大数nums[j],满足nums[j] > nums[i]
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                --j;
            }

            //交换i,j位置的元素
            swap(nums, i, j);
        }

        //交换后,i后面的元素为降序排序,需要反转为升序
        reverse(nums, i + 1);
    }

    //交换数组nums中i,j位置的两个元素
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    //对nums数组[start,nums.length-1]部分进行反转
    private void reverse(int[] nums, int start) {
        int end = nums.length - 1;

        while (start < end) {
            swap(nums, start, end);
            ++start;
            --end;
        }
    }
}
