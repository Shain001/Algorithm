package com.shain.dp.knapsack.zeroOne;

import java.util.Arrays;

/**
 * 依然是 0-1 背包， 这道题是找， 容量为 k的背包里， "最大" 能装多少价值。
 * <p>
 * 即 这道题也是在数组中找一个subset， 使其 sum 等于 target，如果不存在这种subset， 返回小于target的最大值。 但是与 L416 的唯一区别在于， 这道题要返回最大值， 而l416只需返回true false
 * <p>
 * p.s. 这种找 "小于等于"target的最大值的方法， 也可以用在 L416 这种类型上， 无非是返回时判断 dp[target] == target.
 * <p>
 * 也即这是一个 最为接近原始背包问题的问题。
 */
public class LastStoneII_L1049 {
    public static void main(String[] args) {
        var test = new LastStoneII_L1049();

        System.out.println(test.lastStoneWeightII(new int[]{2, 7, 4, 1, 8, 1}));
    }

    public int lastStoneWeightII(int[] stones) {
        int sum = Arrays.stream(stones).sum();

        int target = sum / 2;

        int[] dp = new int[target + 1];

        if (stones[0] < dp.length) {
            for (int i = stones[0]; i < dp.length; i++) {
                dp[i] = stones[0];
            }
        }

        for (int i = 1; i < stones.length; i++) {

            // 选： dp[i][j] = dp[i-1][j-stones[i]]
            // 不选：dp[i][j] = dp[i-1][j]
            for (int j = dp.length - 1; j >= 0; j--) {
                if (j >= stones[i]) {
                    dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
                }
            }
        }

        return sum - dp[target] - dp[target];
    }
}
