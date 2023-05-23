package com.shain.dp.array.stock;

public class BestTimeStockFee_L714 {
    public int maxProfit(int[] prices, int fee) {
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            // 加手续费只需在卖股票时减去手续费即可。
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]-fee);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
        }

        return dp[dp.length-1][0];
    }
}
