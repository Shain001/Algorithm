package com.shain.dp.array.stock;

public class BestTimeStock1_L121 {
    /**
     * 股票类难点在于， 状态多， 选择多， 造成状态转移的路径多。
     *
     * @param prices
     * @return
     */
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
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 第i天手头持有1股， 两种可能性： 前一天 持有了股票， 当天rest -> dp[i-1][1]; 前一天没持有， 当天买了 -> dp[i-1][0]-prices[i] -> wrong: should be 0-prices[i]
            // Why Wrong? 因为题目要求只能交易一次， dp[i-1][0]-prices[i] 相当于不限买卖次数， 为什么？ 因为dp[i-1][0] 可能由两种状态转移而来， 一种是i-2没买， i-1也没买
            // 另一种是 i-2 买了， i-1这一天卖了， you see? 如果买过了就不能再买了， 所以当要求只能买卖一次时， dp[i][1] （第i天持有1股） 只有两种可能： 前一天就持有了， 今天不卖， 以及之前'一直'没持有， 今天第一次买
            // PS: this is what stock 2 mean, and passed after submitted
//            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], 0 - prices[i]);
        }

        return dp[dp.length - 1][0];
    }
}
