package com.shain.dp.array.stock;

public class BestTimeStock2_L122 {
    public int maxProfit(int[] prices) {
        // i = days, j = number of stock you have 0 or 1
        // dp value = profit
        int[][] dp = new int[prices.length][2];
        // 第0天， 手头持有0个股票 -> 当前利润为 0 元
        dp[0][0] = 0;
        // 第0天， 手头持有1个股票 -> 当前利润 -price[0] 元。 why？ 初始手头0元， 花了n元买股票， 所以利润为 0-n 元
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            // 第i 天手头持有0股， 两种可能性： 前一天就没持有股票， 当天 rest -> dp[i-1][0]; 前一天持有了股票， 当天卖了->dp[i-1][0]
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            // difference with stock1_L121 see comments in L121's code.
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
        }

        return dp[dp.length-1][0];
    }
}
