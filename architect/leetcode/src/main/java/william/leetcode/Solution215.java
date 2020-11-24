//package william.leetcode;
//
///**
// * @Author: ZhangShenao
// * @Date: 2019/6/14 14:03
// * @Description:https://leetcode.com/problems/kth-largest-element-in-an-array/
// */
//public class Solution215 {
//    public static void main(String[] args) {
//        int[] nums = {2, 1};
//        int k = 1;
//        Solution215 s = new Solution215();
//        System.err.println(s.findKthLargest(nums, k));
//    }
//
//    //在数组nums中找到第k大的元素(k从0开始),要求算法时间复杂度O(n*logn),空间复杂度为O(1)
//    public int findKthLargest(int[] nums, int k) { }
//
//    //在数组nums中找到第k大的元素(k从0开始)
//    public int findKthLargest(int[] nums, int k) {
//        //目标索引为k-1
//        return findKth(nums, 0, nums.length - 1, k - 1);
//    }
//
//    //在数组arr[]的[left,right]范围内找到第k大的元素(从0开始)
//    private int findKth(int arr[], int left, int right, int k) {
//        if (left >= right) {
//            return arr[left];
//        }
//        int pivot = partition(arr, left, right);
//        if (pivot == k) {
//            return arr[k];
//        }
//        if (pivot > k) {
//            return findKth(arr, left, pivot - 1, k);
//        }
//        return findKth(arr, pivot + 1, right, k);
//
//    }
//
//    //进行partition操作,将数组分成左右两部分,左边元素大于等于基准值,右边元素小于等于基准值
//    private int partition(int arr[], int left, int right) {
//        int pivot = arr[left];
//        int l = left + 1;
//        int r = right;
//        while (l <= r) {
//            while (l <= right && arr[l] >= pivot) {
//                ++l;
//            }
//            while (r >= left + 1 && arr[r] <= pivot) {
//                --r;
//            }
//            if (l >= r) {
//                break;
//            }
//            swap(arr, l, r);
//            ++l;
//            --r;
//
//        }
//        swap(arr, left, r);
//        return r;
//    }
//
//    private void swap(int[] arr, int i, int j) {
//        int tmp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = tmp;
//    }
//}
