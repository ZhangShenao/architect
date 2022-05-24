package william.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/24 下午2:13
 * https://leetcode.cn/problems/3sum/
 */
public class 三数之和_15 {
    //采用排序+双指针
    //首先对数组进行快速排序(递增)
    //然后遍历数组nums[i],在[i+1,n)范围内需要两数之和target=-nums[i]
    //查找时采用双指针法,分别指向数组头、尾
    //如果当前和>target,则向左移动右指针,否则向右移动左指针
    //基于有序数组可以避免重复的情况
    //时间复杂度 O(N*N): 排序O(N*logN)+双指针查找O(N*N)
    //空间复杂度(1)
    public List<List<Integer>> threeSum(int[] nums) {
        //边界条件
        if (nums == null || nums.length < 3) {
            return Collections.emptyList();
        }

        int N = nums.length;

        //首先对数组进行递增排序
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>(); //最终结果

        //遍历数组,使用双指针查找两数和
        for (int i = 0; i < N - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {   //基于排序去重
                continue;
            }

            int target = -nums[i];

            int l = i + 1;
            int r = N - 1;

            while (l < r) { //双指针查找两数和
                int sum = nums[l] + nums[r];
                if (sum == target) {  //找到加和,保存结果
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(nums[i]);
                    tmp.add(nums[l]);
                    tmp.add(nums[r]);
                    result.add(tmp);

                    //移动两指针
                    while (l < r && nums[l] == nums[l + 1]) {   //去重
                        ++l;
                    }
                    ++l;
                    while (l < r && nums[r] == nums[r - 1]) {   //去重
                        --r;
                    }
                    --r;
                } else if (sum > target) {
                    --r;
                } else {
                    ++l;
                }
            }

        }

        return result;
    }

    public static void main(String[] args) {
        三数之和_15 s = new 三数之和_15();
        int[] nums = new int[]{0, 0, 0, 0, 0};
        List<List<Integer>> result = s.threeSum(nums);
        System.out.println(result);
    }
}
