package com.shain.dp.twoPointer.twoString;

public class RegularExpression_L10 {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (p.charAt(j) != '.' && p.charAt(j) != '*') {
                    dp[i][j] = i == j && p.charAt(j) == s.charAt(i);
                    continue;
                }

                if (p.charAt(j) == '*') {

                }

            }
        }


        return dp[s.length()][p.length()];
    }
}
