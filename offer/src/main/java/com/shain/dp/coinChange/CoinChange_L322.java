package com.shain.dp.coinChange;

import java.util.Arrays;

public class CoinChange_L322 {
    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1, 2, 5}, 11));
    }

    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        // 此处初始化为amount+1是可以的， 因为极限情况就是 有一块钱的硬币， 最多 amount枚硬币
        // 所以return时用 result == amount + 1 判断不会有使用 Integer.max 的问题， 即将得到的正确答案判断为-1
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
//            int curMin = Integer.MAX_VALUE;
            for (Integer coin : coins) {
                if (i - coin >= 0)
                    dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
            }
        }

        return dp[amount] == amount+1? -1: dp[amount];
    }
}