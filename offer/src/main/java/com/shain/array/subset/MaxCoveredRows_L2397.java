package com.shain.array.subset;

/**
 * https://leetcode.cn/circle/discuss/CaOJ45/
 *
 * https://leetcode.cn/problems/maximum-rows-covered-by-columns/solutions/1798794/by-endlesscheng-dvxe/
 */
public class MaxCoveredRows_L2397 {
    public int maximumRows(int[][] matrix, int numSelect) {
        int cols = matrix[0].length;
        int ans = 0;

        // 将每一行的 1 的信息统计在数组中
        int[] zip = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j =0 ; j < cols; j++) {
                // zip[i] 代表的是 matrix中 第 i 行 1 的分布信息。
                // e.g. matrix[i] = 1, 0, 0, 1
                // 那么 zip[i] 中存的值， 其实就是 1,0,0,1 分布的二进制值。
                // 也即 1001 ， 但是注意不要混淆， 这里 二进制的 第 i 位 （低位到高位的 第i为， 也即右边数第i为）就表示这 matrix中的第j列
                // 要计算出这个值， 只需要如下位运算。
                // zip[i] 初始值是0， 只需要不断更细 第 j 位上1 的信息， 所以 取 或即可。
                // 这里多说一嘴， 这样一来， 其实就相当于为 matrix 的每一行建立了一个单独的 subset， 这个subset中包含全部 值 为 1 的个子的 列信息。
                zip[i] |= matrix[i][j] << j;
            }
        }


        // 遍历所有 可能的 列 子集， 通过 if 检查 选取的子集的个数是否等于 numselect
        // 然后 遍历所有行， 根据上面求的每一行1的分布信息， 看当前 的 列子集 是否包含该行所有的1
        for (int i = 0; i < (1<<cols); i++) {
            if (Integer.bitCount(i) != numSelect) {
                continue;
            }
            int temp = 0;
            for (int r = 0; r < matrix.length; r++) {
                // 如果包含了所有的1， 或者 这一行的值全是0， 当前列的 集合的值 与上 改行 1 所在的列的集合的值 就等于 改行 1 所在的列的集合的值
                if ((i & zip[r]) == zip[r]) {
                    temp++;
                }
            }

            ans = Math.max(ans, temp);
        }

        return ans;
    }
}
