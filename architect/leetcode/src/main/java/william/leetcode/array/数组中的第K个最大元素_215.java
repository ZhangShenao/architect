package william.leetcode.array;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/21 下午3:29
 * <p>
 * https://leetcode.cn/problems/kth-largest-element-in-an-array/
 */
public class 数组中的第K个最大元素_215 {
    //利用快速排序+二分查找的思想
    //每次对数组进行partition操作,找到基准值的位置,并与k进行比较
    //时间复杂度O(N)
    //空间复杂度O(1)
    public int findKthLargest(int[] nums, int k) {
        //边界条件
        if (nums == null || nums.length == 0 || k < 1 || nums.length < k) {
            return -1;
        }

        //基于快速排序+二分查找的思想,计算partition,升序。第k大的元素下标为nums.length-k
        int target = nums.length - k;

        int l = 0;
        int r = nums.length - 1;
        //partition+二分查找
        while (l <= r) {
            int partition = partition(nums, l, r);
            if (partition == target) {
                return nums[partition];
            }
            if (partition > target) {
                r = partition - 1;
            } else {
                l = partition + 1;
            }
        }

        return -1;
    }

    //对数组nums[l,r]部分进行partition操作
    private int partition(int[] nums, int l, int r) {
        //选取第一个元素作为基准值
        int pivot = nums[l];

        //双指针快排
        int i = l;
        int j = r;

        while (true) {
            while (i <= r && nums[i] <= pivot) {
                i++;
            }
            while (j > l && nums[j] >= pivot) {
                j--;
            }
            if (i >= j) {
                break;
            }
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

        nums[l] = nums[j];
        nums[j] = pivot;
        return j;
    }

    public static void main(String[] args) {
        数组中的第K个最大元素_215 s = new 数组中的第K个最大元素_215();
        int[] nums = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k = s.findKthLargest(nums, 4);
        System.out.println(k);
    }
}
