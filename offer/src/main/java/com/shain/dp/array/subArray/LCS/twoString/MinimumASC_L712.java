package com.shain.dp.array.subArray.LCS.twoString;

public class MinimumASC_L712 {
    public int minimumDeleteSum(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 1; i < dp.length; i++) {
            // wrong, same wrong when initializing below
            // 第一行第一列是 0-i， 0-j 删除到空字符所需要的asc码值
            // you know， just forgot
//            dp[i][0] = s1.charAt(i-1);

            dp[i][0] = s1.charAt(i - 1) + dp[i - 1][0];
        }

        for (int j = 1; j < dp[0].length; j++) {
            dp[0][j] = s2.charAt(j - 1) + dp[0][j - 1];
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                char c1 = s1.charAt(i - 1);
                char c2 = s2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1];
                    continue;
                }

                dp[i][j] = getMin(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1], c1, c2);
            }
        }

        return dp[dp.length - 1][dp[0].length - 1];
    }

    private int getMin(int deleteL, int deleteR, int deleteBoth, int c1, int c2) {
        int temp = Math.min(deleteL + c1, deleteR + c2);
        return Math.min(deleteBoth + c1 + c2, temp);
    }
}
