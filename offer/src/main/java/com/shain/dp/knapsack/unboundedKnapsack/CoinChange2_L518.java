package com.shain.dp.knapsack.unboundedKnapsack;

public class CoinChange2_L518 {
    public static void main(String[] args) {
        System.out.println(change(10, new int[]{5}));
    }


    public static int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length+1][amount + 1];

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }

        // 该题不应该这样初始化。
        // 这样初始化以后， 其实相当于默认一个元素只能用一次， 也即这是适用于0-1 问题的初始化方法。
        // why? 假设 input 为 nums = [5], amount = 10,  这样会只更新到 dp[5]=1, 而漏掉了 dp[10]=1。
        // 所以对于完全背包问题， dp的长宽都应该是 +1。
//        if (coins[0] < dp[0].length) {
//            dp[0][coins[0]] = 1;
//        }

        // i-> coins, j -> amount
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (j - coins[i-1] < 0) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }

                // wrong version
                // 错误的点在于： 对于i 指向的元素， 其可能性应该等于 选和不选之和
                // 而不是 在选时， 仅仅对dp[i][j - coins[i]] + 1。
                // "可能性应该等于 选和不选之和" 好理解， 那么 +1 为什么是错的？
                // 因为：
                // +1 本身就是错的。 假设当前元素是5， index = 10， target=6。
                // 如果选5， 那么剩余的target=1， 你在 0-10 之间 找到 target=6-5=1 的方案数， 其实就是在0-10之间找到6的方案数， 不应该+1。
//                if (dp[i][j - coins[i]] != 0) {
//                    dp[i][j] = 1 + dp[i][j - coins[i]];
//                } else {
//                    dp[i][j] = dp[i - 1][j];
//                }

                dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i-1]];

            }
        }

        return dp[dp.length - 1][dp[0].length - 1];
    }
}
