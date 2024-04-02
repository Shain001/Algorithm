package com.shain.dp;

/**
 * f(i,j)表示以(i,j)为右下角的正方形的最大边长，如果 (i,j)为“0”，以(i,j)为右下角不可能构成全为“1”的正方形f(i,j)=0，如果(i,j)为“1”，
 * 至少可以获得边长为1的正方形，还能不能变大只能向左向上扩展边长，这个时候需要看正上，左边和左上三个点，因为扩展定会将这三个相邻点包含进来，
 * 如果三个点中最小值为0，那么扩展后肯定不行，如果最小值为1，那么三个点都为1，定能扩展成边长为2的正方形，同理能扩展到最大的是 min(左，上，左上) + 1。
 *
 * 以下用一个例子具体说明。原始矩阵如下。
 *
 * 0 1 1 1 0
 * 1 1 1 1 0
 * 0 1 1 1 1
 * 0 1 1 1 1
 * 0 0 1 1 1
 * 对应的 dp\textit{dp}dp 值如下。
 *
 * 0 1 1 1 0
 * 1 1 2 2 0
 * 0 1 2 3 1
 * 0 1 2 3 2
 * 0 0 1 2 3
 *
 */
public class MaxSquare_L221 {
    public int maximalSquare(char[][] matrix) {
        int n = matrix[0].length;
        int m = matrix.length;
        int[][] dp = new int[m][n];
        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    dp[i][j] = 0;
                    continue;
                }

                if (i-1 >= 0 && j-1 >= 0) {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                } else {
                    dp[i][j] = 1;
                }

                ans = Math.max(ans, dp[i][j]);
            }
        }

        return ans*ans;
    }
}
