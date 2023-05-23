package com.shain.dp.array.stock;

public class BestTimeStockColdDown_L309 {
    public int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[1][0] = Math.max(dp[0][1] + prices[1], 0);
        dp[1][1] = Math.max(dp[0][1], -prices[1]);

        for (int i = 2; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]+prices[i]);
            // 含冷冻期对于dp方程的唯一影响， 就是在你买股票的前一天，是不可能卖股票的。
            // 这意味着， 他只会对dp[i][1]产生影响， 因为"买股票" 对应这 持有 1 股， 也即dp[i][1]
            // 而其唯一的影响， 就是 当你买股票时， 这一买股票的状态， 需从i-2这一天转移过来。
            // Specifically, "买" 这一动作，对应这 dp[i][1]
            // dp[i][1] 可能从两种状态转移： 1. dp[i-1][1] -> 昨天你就持有， 今天你不动 -> 不被冷冻期影响。
            // 2. dp[i-1][0] - prices[i]， 代表今天你买入了，花了n元。
            // 注意， dp[i-1][0] 有可能从哪种可能性来？ dp[i-2][0] -> i-2这天就没有，i-1没动， 2. dp[i-2][1]+prices[i-1] -> i-2这天你持有股票， i-1这天你卖了。
            // 我们要消除的， 就是line22 中第二种情况， 去除掉以后只剩第一种情况，即dp[i-2][0]
            dp[i][1] = Math.max(dp[i-1][1], dp[i-2][0] - prices[i]);
        }

        return dp[dp.length-1][0];
    }
}
