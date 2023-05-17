package com.shain.dp.pathSum.preTraverse;

public class MinimumPathSum_L64 {
    public static void main(String[] args) {
        minPathSum(new int[][]{{1, 2, 3}, {4, 5, 6}});
    }

    public static int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for (int j = 1; j < dp[0].length; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (j - 1 < 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
                }
            }
        }

        return dp[dp.length - 1][dp[0].length - 1];
    }

}
