package com.shain.array.prefixSum;

/**
 * 2 维前缀和
 * 要点在于：
 * 1） 计算 i,j 到原点组成的矩形内所有元素的和
 * 2） 通过图形相减， 得到区间内和
 */
public class RangeSumTwoD_L304 {
    private final int[][] prefixSum;

    public RangeSumTwoD_L304(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        prefixSum = new int[rows+1][cols+1];
        prefixSum[0][0] = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // NOTE prefixSum[i][j] 的意义为 数组中i， j 到0,0 所组成的矩形的所有元素之和
                // wrong:
                // prefixSum[i+1][j+1] = prefixSum[i][j] + matrix[i][j];
                prefixSum[i + 1][j + 1] = prefixSum[i][j + 1] + prefixSum[i + 1][j] - prefixSum[i][j] + matrix[i][j];
            }
        }

    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return prefixSum[row2 + 1][col2 + 1] - prefixSum[row1][col2 + 1] - prefixSum[row2 + 1][col1] + prefixSum[row1][col1];
    }


}
