package com.shain.dp.knapsack.unboundedKnapsack;

public class CombinationSum4_L377 {
    public int combinationSum4(int[] nums, int target) {
        int dp[] = new int[target + 1];
        dp[0] = 1;

        // 选：dp[i][j-n] buxuan: dp[i][j]
        for (int i = 0; i < target + 1; i++) {
            for (Integer n : nums) {
                if (n > i)
                    continue;
                dp[i] = dp[i - n] + dp[i];
            }
        }

        return dp[target];
    }
}
