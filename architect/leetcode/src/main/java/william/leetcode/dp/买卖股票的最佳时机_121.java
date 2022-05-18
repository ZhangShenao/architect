package william.leetcode.dp;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/18 下午8:15
 * <p>
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/
 */
public class 买卖股票的最佳时机_121 {
    //采用类似于炒股中抄底的方式,找到股价的最低点的那天,然后判断哪天卖出收益最高
    //遍历一次prices数组,同时更新股价的最低点和最大收益
    //时间复杂度O(N),仅遍历一次数组
    //空间复杂度O(1)
    public int maxProfit(int[] prices) {
        //边界条件
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int maxProfit = 0;//记录最大收益
        int minPrice = Integer.MAX_VALUE;   //记录股价最低点

        //遍历数组,更新最大收益和最低股价
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) { //当天是股价最低点,更新最低股价
                minPrice = prices[i];
            } else {    //当天不是最低点,判断卖出收益
                int profit = prices[i] - minPrice;
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }

        //返回最大收益
        return maxProfit;
    }
}
