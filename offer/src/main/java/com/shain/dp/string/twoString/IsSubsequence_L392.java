package com.shain.dp.string.twoString;

public class IsSubsequence_L392 {
    public boolean isSubsequence(String s, String t) {
        // dp含义： 在 t 字符串 0-j 范围内， 包含多少个 0-i 范围的 字符。
        int[][] dp = new int[s.length() + 1][t.length() + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp.length; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    // t 的当前元素不等于 s的当前元素， 那么就看看 t 的0-j-1 之内有多少s 0-i；
                    //为什么我们不使用 dp[i-1][j-1] 呢？因为 dp[i-1][j-1] 表示的是在 t 的前 j-1 个字符中有多少个 s 的前 i-1 个字符的子序列。然而，在第二种情况中，我们并没有减少我们要在 s 中匹配的字符数量，我们只是忽略了 t 的一个额外的字符。我们仍然想知道在 t 的前 j-1 个字符中有多少个 s 的前 i 个字符的子序列，而这正是 dp[i][j-1] 给我们的信息。
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        return dp[dp.length - 1][dp[0].length - 1] == s.length();
    }
}
