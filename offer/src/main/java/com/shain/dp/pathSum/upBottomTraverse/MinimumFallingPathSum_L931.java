package com.shain.dp.pathSum.upBottomTraverse;

public class MinimumFallingPathSum_L931 {
    public static void main(String[] args) {
        System.out.println(minFallingPathSum(new int[][]{{-19, 57}, {-40, -5}}));
    }

    // 该题无法写成一维dp数组， 因为无论你是正序还是倒序更新一维dp， 更新过程中都会更新到 当前格子左上方和右上方的格子
    // e.g. dp = 1, 2, 3； 表示第i-1， j。 遍历第i行时，更新到 i，j 时 1 变成4，
    // 而遍历到 i, j+1 -> 2 时， 需要用到 i-1, j-1， 也即原本的1， 但是 i-1, j-1 格子值的1 已经被更新成了4， 进而更新到 i+1, j+1时结果出错
    public static int minFallingPathSum(int[][] matrix) {

        int[][] dp = new int[matrix[0].length][matrix.length];
        System.arraycopy(matrix[0], 0, dp[0], 0, matrix[0].length);

        for (int i = 1; i < matrix.length; i++) {
            for (int j = dp.length - 1; j >= 0; j--) {
                int above = dp[i - 1][j];
                int la = j - 1 >= 0 ? dp[i - 1][j - 1] : Integer.MAX_VALUE;
                int ra = j + 1 <= matrix.length - 1 ? dp[i - 1][j + 1] : Integer.MAX_VALUE;
                dp[i][j] = matrix[i][j] + Math.min(above, Math.min(la, ra));

//                if (i == matrix.length - 1)
//                    result = Math.min(result, dp[i][j]);
            }
        }

        // result 的比较写在这里好， 写在line21的话对于 matrix.length=1的情况不好处理， 需要特判
        // e.g. matrix = [[3]]
        int result = Integer.MAX_VALUE;
        for (int i = matrix.length - 1; i < matrix[0].length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                result = Math.min(result, dp[i][j]);
        return result;
    }

    // 但是可以优化成没有dp数组
    // copy from answer
    public int minFallingPathSum_noArray(int[][] A) {
        int N = A.length;
        for (int r = N - 2; r >= 0; --r) {
            for (int c = 0; c < N; ++c) {
                // best = min(A[r+1][c-1], A[r+1][c], A[r+1][c+1])
                int best = A[r + 1][c];
                if (c > 0)
                    best = Math.min(best, A[r + 1][c - 1]);
                if (c + 1 < N)
                    best = Math.min(best, A[r + 1][c + 1]);
                A[r][c] += best;
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int x : A[0])
            ans = Math.min(ans, x);
        return ans;
    }
}
