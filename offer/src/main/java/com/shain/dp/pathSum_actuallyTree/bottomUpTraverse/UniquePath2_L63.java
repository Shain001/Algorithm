package com.shain.dp.pathSum_actuallyTree.bottomUpTraverse;

public class UniquePath2_L63 {
    public static void main(String[] args) {
        var input = new int[][]{{0,0,0}, {0,1,0}, {0,0,0}};
        var test = new UniquePath2_L63();
        System.out.println(test.uniquePathsWithObstacles(input));
    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        dp[m-1][n-1] = obstacleGrid[m-1][n-1] == 1? 0: 1;

        for (int i = n-2; i >= 0; i--) {
            dp[m-1][i] = obstacleGrid[m-1][i] == 1? 0: dp[m-1][i+1];
        }

        for (int i = m-2; i >= 0; i--) {
            dp[i][n-1] = obstacleGrid[i][n-1] == 1? 0: dp[i+1][n-1];
        }

        for (int i = m-2; i >= 0; i--) {
            for (int j = n-2; j>= 0; j--) {
                dp[i][j] = obstacleGrid[i][j] == 1? 0: (dp[i+1][j] + dp[i][j+1]);
            }
        }

        return dp[0][0];
    }
}
