package william.leetcode.array;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/20 上午11:22
 * <p>
 * https://leetcode.cn/problems/container-with-most-water/
 */
public class 盛最多水的容器_11 {
    //采用双指针法
    //一个指针指向数组开头,另一个指针指向数组末尾,两指针相向移动
    //每次计算两个指针之间的面积,并与最大面积进行比较
    //每次移动高度较小的指针
    //时间复杂度O(N) 仅需遍历一次数组
    //空间复杂度O(1)
    public int maxArea(int[] height) {
        //边界条件
        if (height == null || height.length == 0) {
            return 0;
        }
        if (height.length == 1) {
            return height[0];
        }

        //定义双指针
        int l = 0;
        int r = height.length - 1;

        //定义最大面积
        int maxArea = 0;

        //双指针相向遍历数组
        while (l < r) {
            //计算当前面积
            int x = height[l];
            int y = height[r];
            int area = Math.min(x, y) * (r - l);

            //更新最大面积
            if (area > maxArea) {
                maxArea = area;
            }

            //移动高度较小的指针
            if (x <= y) {
                ++l;
            } else {
                --r;
            }
        }

        //返回最大面积
        return maxArea;
    }
}
