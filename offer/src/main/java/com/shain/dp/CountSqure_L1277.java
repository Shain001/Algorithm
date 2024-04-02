package com.shain.dp;

/**
 * 与 L221 一样的思路。
 *
 * dp[i][j] 表示一 i，j 为右下角的正方形的最大边长。
 * 假如 以  i， j 为右下角的最大边长 为 3， 那么以 i，j 为右下角的 正方形个数也就是 3 个。
 */
public class CountSqure_L1277 {
    public int countSquares(int[][] matrix) {
        int n = matrix[0].length;
        int m = matrix.length;
        int[][] dp = new int[m][n];
        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                if (i-1 >= 0 && j-1 >= 0) {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                } else {
                    dp[i][j] = 1;
                }

                ans += dp[i][j];
            }
        }

        return ans;
    }
}
