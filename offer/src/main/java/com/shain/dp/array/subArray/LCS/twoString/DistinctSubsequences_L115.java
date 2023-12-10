package com.shain.dp.array.subArray.LCS.twoString;

public class DistinctSubsequences_L115 {
    public int numDistinct(String s, String target) {

        // dp 含义： s的 0-i 中， 包含 target 0-j 这些元素 的 子集数量。
        // 子集数量的判断：
        // 当我们看到 s 的第 i 个字符与 target 的第 j 个字符相等时，我们有两种选择：

        // a. 包含 s 的第 i 个字符：我们可以选择包含 s 的第 i 个字符作为子序列的一部分。在这种情况下，我们需要查看 s 的前 i-1 个字符中有多少个不同的子序列等于 target 的前 j-1 个字符，这是 dp[i-1][j-1]。

        // b. 不包含 s 的第 i 个字符：我们也可以选择不包含 s 的第 i 个字符。这意味着我们需要查看 s 的前 i-1 个字符中有多少个不同的子序列等于 target 的前 j 个字符，这是 dp[i-1][j]。

        // 综合这两种选择，s 的前 i 个字符中等于 target 的前 j 个字符的不同子序列的数量是 dp[i-1][j-1] + dp[i-1][j]。

        // 让我们通过一个例子来解释这个：

        // 假设 s = "rabbbit" 且 target = "rabbit"。

        // 当 i = 4 (s 的字符是 b) 和 j = 3 (target 的字符是 b) 时，rabb / rab 我们有两种选择：

        // 包含 s 的第 4 个字符：在 s 的前 3 个字符 "rab" 中有 1 个 "rab" 等于 target 的前 2 个字符 "ra" 的子序列。
        // 不包含 s 的第 4 个字符：在 s 的前 3 个字符 "rab" 中有 1 个 "rab" 等于 target 的前 3 个字符 "rab" 的子序列。
        // 综合这两种情况，dp[4][3] 应该是 1 + 1 = 2。
        int[][] dp = new int[s.length() + 1][target.length() + 1];

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }

        // i-> s; j -> target
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (s.charAt(i - 1) == target.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[dp.length - 1][dp[0].length - 1];
    }
}
