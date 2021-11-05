package william.algo.greedy;

/**
 * @Author zhangshenao
 * @Date 2021-11-05
 * @Description https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 * 采用贪心算法：遍历数组,如果发现后一天比当前的股价高，则在当前买进，在后一天卖出 时间复杂度O(n)
 */
public class 买卖股票的最佳时机_122 {
    public int maxProfit(int[] prices) {
        //边界
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            //如果后一天比当前股价高，则在当前买进，在后一天卖出
            int today = prices[i];
            int tomorrow = prices[i + 1];
            if (tomorrow > today) {
                profit += (tomorrow - today);
            }
        }

        return profit;
    }
}
