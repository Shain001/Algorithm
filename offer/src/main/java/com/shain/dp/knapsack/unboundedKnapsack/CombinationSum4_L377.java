package com.shain.dp.knapsack.unboundedKnapsack;

// 求排列数
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

    // 二维数组版本， 可以解释为什么 "就算竖着更新 二维dp数组也无法得到正确答案"
    // 关键原因是， 这题根 完全背包问题不完全一样
    // https://leetcode.cn/problems/combination-sum-iv/solutions/740651/gong-shui-san-xie-yu-wan-quan-bei-bao-we-x0kn/
    // dp数组这里定义的， 是 " '组合长度为' i， 得到 target的组合数， 而 长度为 i 的组合， 最后一位可以是 任何一个 数组中的数字 "
    // 而 正儿八经的 背包问题的dp定义是 "数组中 前 i 个元素， 能够得到的 target 组合数或者最小值等等， 取决于答案问的是什么 "
    // 而这个 二维数组 优化以后， 就得到了 上面的 一维dp版本， 所以说这题其实不是一个 正儿八经的 背包问题
    public int combinationSum4_v2(int[] nums, int t) {
        // 因为 nums[i] 最小值为 1，因此构成答案的最大长度为 target
        int len = t;
        int[][] f = new int[len + 1][t + 1];
        f[0][0] = 1;
        int ans = 0;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= t; j++) {
                // 重点在这， 即  长度为 i 的组合， 最后一位可以是 任何一个 数组中的数字
                for (int u : nums) {
                    if (j >= u) f[i][j] += f[i - 1][j - u];
                }
            }
            // 所有可能方案 由 长度为 1 - i 所有组合长度的 方案数总和
            ans += f[i][t];
        }
        return ans;
    }

}
