package com.shain.dp.array.subArray.LIS;

// 找的重复自数组需要连续

/**
 * 注意dp数组的定义。 为 0-i 与 0-j 之间 重复数组的长度， 注意是长度， 不是最大长度。
 *
 * 这题dp的意义在于， 节省了 暴力破解中的重复求解过程。 题解说的很好， 如下：
 *
 * 暴力解法的过程中，我们发现最坏情况下对于任意 i 与 j ，A[i] 与 B[j] 比较了 min⁡(i+1,j+1)\min(i + 1, j + 1)min(i+1,j+1) 次。这也是导致了该暴力解法时间复杂度过高的根本原因。
 *
 * 不妨设 A 数组为 [1, 2, 3]，B 两数组为为 [1, 2, 4] ，那么在暴力解法中 A[2] 与 B[2] 被比较了三次。这三次比较分别是我们计算 A[0:] 与 B[0:] 最长公共前缀、 A[1:] 与 B[1:] 最长公共前缀以及 A[2:] 与 B[2:] 最长公共前缀时产生的。
 *
 * 我们希望优化这一过程，使得任意一对 A[i] 和 B[j] 都只被比较一次。这样我们自然而然想到利用这一次的比较结果。如果 A[i] == B[j]，那么我们知道 A[i:] 与 B[j:] 的最长公共前缀为 A[i + 1:] 与 B[j + 1:] 的最长公共前缀的长度加一，否则我们知道 A[i:] 与 B[j:] 的最长公共前缀为零。
 *
 * 这样我们就可以提出动态规划的解法：令 dp[i][j] 表示 A[i:] 和 B[j:] 的最长公共前缀，那么答案即为所有 dp[i][j] 中的最大值。如果 A[i] == B[j]，那么 dp[i][j] = dp[i + 1][j + 1] + 1，否则 dp[i][j] = 0。
 *
 *
 * 题解写的是从后往前更新dp， 我是从前往后。
 *
 * todo： 1. 这算是 LCS 还是LIS 还是无所谓？ 什么情况下 dp表示的是最大值？-> 看 string那里的题， 判断区别。 3. 数组中要求的解是连续的到底有什么 根源性意义？ 带总结。
 */
public class MaxLenOfRepeatedArraryContinous_L718 {
    public int findLength(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        int result = 0;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }

                result = Math.max(result, dp[i][j]);
            }
        }

        return result;
    }

    public int findLength_simplified(int[] nums1, int[] nums2) {
        int[] dp = new int[nums2.length + 1];
        int result = 0;

        for (int i = 0; i < nums1.length; i++) {
            for (int j = dp.length - 1; j > 0; j--) {
                if (nums1[i] == nums2[j - 1]) {
                    dp[j] = dp[j - 1] + 1;
                    result = Math.max(result, dp[j]);
                } else {
                    dp[j] = 0;
                }

            }
        }


        return result;
    }

    // todo: 滑动窗口

}
