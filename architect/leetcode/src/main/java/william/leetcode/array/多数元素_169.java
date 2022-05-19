package william.leetcode.array;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/19 上午8:02
 * https://leetcode.cn/problems/majority-element/
 */
public class 多数元素_169 {
    //采用Boyer-Moore投票算法
    //记录当前存活的元素和存活的数量
    //遍历数组,依次将每个元素与存活的元素进行比较,如果不相等,就将两元素对消掉,并将存活计数-1。如果相等,就将存活元素的数量+1
    //最后剩余的存活元素就是多数元素
    //时间复杂度O(N) 仅需遍历一次数组
    //空间复杂度O(1)
    public int majorityElement(int[] nums) {
        //边界条件
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }

        //初始化存活元素和计数
        int aliveElement = nums[0];
        int aliveCount = 1;

        //遍历数组,依次与当前存活的元素进行比较
        for (int i = 1; i < nums.length; i++) {
            int n = nums[i];
            if (aliveCount == 0) {  //如果没有存活元素,则将当前元素设置为存活元素,并初始化存活计数
                aliveElement = n;
                aliveCount = 1;
            } else { //当前存在存活元素,需要将两元素进行对比
                if (n == aliveElement) {  //如果当前元素与存活元素相等,则将存活元素计数+1
                    ++aliveCount;
                } else {    //如果两元素不相等,则将两元素同时对消掉,并将存活计数-1
                    --aliveCount;
                }
            }
        }

        //最终返回存活元素
        return aliveElement;
    }

    public static void main(String[] args) {
        多数元素_169 s = new 多数元素_169();
        int e = s.majorityElement(new int[]{3, 3, 4});
        System.out.println(e);
    }
}
