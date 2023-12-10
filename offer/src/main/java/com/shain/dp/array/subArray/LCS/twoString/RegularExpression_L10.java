package com.shain.dp.array.subArray.LCS.twoString;

/**
 * 难点在于对 * 的处理
 * <p>
 * 从 * 匹配 前一个字符的次数 入手比较好理解。 * 可以代表前一个字符出现 0 次 1 次 或 多次
 */
public class RegularExpression_L10 {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        //初始化
        // p 为空串，s 不为空串，肯定不匹配。
        // s 为空串，但 p 不为空串，要想匹配，只可能是右端是星号，它干掉一个字符后，把 p 变为空串。
        // s、p 都为空串，肯定匹配
        for (int j = 1; j < n + 1; j++) {
            if (p.charAt(j - 1) == '*')
                dp[0][j] = dp[0][j - 2];
        }

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                // 1. 相等 -> i-1 j-1
                // 2. 不想等
                // 2.1 p = . -> i-1 j-1
                // 2.2 p = *
                // s 当前字符， 是否等于 p j-1 或者 p j-1 是否是 .
                // 如果等于： i-1 j
                // 如果不等于， 也可以把x*丢掉， 即 i j-2
                char curS = s.charAt(i - 1);
                char curP = p.charAt(j - 1);
                if (curS == curP || curP == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }

                if (curP == '*') {
                    if ((curS == p.charAt(j - 2) || p.charAt(j - 2) == '.'))
                        // dp[i-1][j] 匹配 >= 2 times, dp[i-1][j-2] 0> one times, dp[i][j-2] 0 times
                        // 这里仍然需要0次的情况， 因为包含了 p j-2 = . 这种情况。 不加0次这个，  "..*" 这个用例会错。
                        dp[i][j] = dp[i - 1][j] || dp[i - 1][j - 2] || dp[i][j - 2];
                    else if (j >= 2)
                        //  * 匹配0次
                        dp[i][j] = dp[i][j - 2];
                }
            }
        }

        return dp[m][n];
    }
}
