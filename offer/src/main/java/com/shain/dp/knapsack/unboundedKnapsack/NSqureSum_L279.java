package com.shain.dp.knapsack.unboundedKnapsack;

public class NSqureSum_L279 {
    public int numSquares(int n) {
        if (n <= 3) {
            return n;
        }

        int[] dp = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            dp[i] = n;
        }

        for (int j = 1; j <= n; j++) {
            for (int i = 1; i * i <= j; i++) {
                dp[j] = Math.min(dp[j - i * i] + 1, dp[j]);

            }
        }

        return dp[n];
    }
}
