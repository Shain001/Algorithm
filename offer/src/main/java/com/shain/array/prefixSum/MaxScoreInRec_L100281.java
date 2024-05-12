package com.shain.array.prefixSum;

import java.util.List;

/**
 * 给你一个由 正整数 组成、大小为 m x n 的矩阵 grid。你可以从矩阵中的任一单元格移动到另一个位于正下方或正右侧的任意单元格（不必相邻）。从值为 c1 的单元格移动到值为 c2 的单元格的得分为 c2 - c1 。
 *
 * 你可以从 任一 单元格开始，并且必须至少移动一次。
 *
 * 返回你能得到的 最大 总得分。
 *
 * https://leetcode.cn/problems/maximum-difference-score-in-a-grid/description/
 *
 *
 * 题目描述虽然是可以走很多步， 但是由于是求最大值， 其实就是相当于走一步。
 *
 * 类似于二维累加和的处理，看这个题解， 懒得写解释了：
 * https://leetcode.cn/problems/maximum-difference-score-in-a-grid/solutions/2774823/nao-jin-ji-zhuan-wan-dppythonjavacgo-by-swux7/
 */
public class MaxScoreInRec_L100281 {
    List<List<Integer>> grid;
    public int maxScore(List<List<Integer>> grid) {
        this.grid = grid;
        int cols = grid.get(0).size();
        int rows = grid.size();
        // 以 0，0 为左上角， i，j 为右下角的矩形中的最小值。
        int[][] min = new int[rows+1][cols+1];
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < cols+1; i++) {
            min[0][i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < rows+1; i++) {
            min[i][0] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j< cols; j++) {
                int cur = get(i, j);
                // 计算当前矩形最小值
                min[i+1][j+1] = Math.min(min[i+1][j], Math.min(cur, min[i][j+1]));

                // 更新ans。
                // prevMin 是 不包含当前列的左侧矩形， 以及不包含当前行的上方矩形的最小值。
                // cur - prevMin 则为 以当前格子为终点的最大差值。
                int prevMin = Math.min(min[i+1][j], min[i][j+1]);
                ans = Math.max(ans, cur-prevMin);
            }
        }

        return ans;
    }

    private int get(int i , int j) {
        return grid.get(i).get(j);
    }
}
