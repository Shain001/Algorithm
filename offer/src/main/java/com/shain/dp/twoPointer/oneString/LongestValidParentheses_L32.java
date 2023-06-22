package com.shain.dp.twoPointer.oneString;

public class LongestValidParentheses_L32 {
    public static void main(String[] args) {
        LongestValidParentheses_L32 test = new LongestValidParentheses_L32();
        System.out.println(test.longestValidParentheses("(()"));
    }
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int[][] dp = new int[s.length()][s.length()];

        for (int i = 0; i <= s.length() - 2; i++) {
            int j = i + 1;
            dp[i][j] = s.charAt(i) == '(' && s.charAt(j) == ')' ? 1 : 0;
        }

        for (int L = 3; L <= s.length(); L++) {
            for (int i = 0; i <= s.length() - L; i++) {
                int j = i + L - 1;

                if (s.charAt(i) == '(' && s.charAt(j) == ')') {
                    if (L == 3) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.max(1 + dp[i + 1][j - 1], Math.max(dp[i][j - 1], dp[i + 1][j]));
                    }
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][dp.length - 1];
    }

}
