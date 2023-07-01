package com.shain.dp.array.stock;

public class BestTimeStock4_L188 {
    /**
     * 与 stock 3 是一样的。
     * <p>
     * stock 3 中 是限制买卖2次，
     * 所以 对于 每一天， 我们创建了 2* 2 + 1 = 5 种状态。 对应 5 个column。
     * 那么 当限制 k 次， 我们则需创建 2 * k + 1 🀄种状态。 因为 没次买卖对应两个状态 （如果忘了看 stock3 就想起来了）。
     * <p>
     * 进而， 无非是 更新dp时， 分奇偶更新。 奇数则是买入， 偶数则是卖出。
     * <p>
     * 唯一要想一下的就是 状态转移方程中， 前一次 买/卖 对应的 column 号是多少， 其实就是很简单的 j-1。 But you realized it anyway by yourself.
     * So I believe you would be all good.
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        int[][] dp = new int[prices.length][2 * k + 1];
        // i = 0 -> 第1次持有0股， 即第一次买入之前
        dp[0][0] = 0;

        for (int i = 1; i < 2 * k + 1; i++) {
            if (i % 2 == 1) {
                // 买入操作
                dp[0][i] = -prices[0];
            } else {
                // 卖出操作
                dp[0][i] = 0;
            }
        }

        for (int i = 1; i < prices.length; i++) {
            // dp[i][0] 代表从未买入， so it's always 0
            for (int j = 1; j < 2 * k + 1; j++) {
                // 买入操作, 表示持有1股 -> i-1 就持有1股， 或 i-1 还没持有
                if (j % 2 == 1) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] - prices[i]);
                } else { // 卖出操作, 表示持有0股 -> i-1 就持有0股， 或i-1 已经持有1股， 今天卖了
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] + prices[i]);
                }
            }
        }

        return dp[dp.length - 1][2 * k];
    }
}
