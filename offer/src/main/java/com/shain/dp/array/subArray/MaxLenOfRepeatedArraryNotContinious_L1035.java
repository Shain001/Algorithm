package com.shain.dp.array.subArray;

/**
 * 在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。
 *
 * 现在，可以绘制一些连接两个数字 nums1[i]和 nums2[j]的直线，这些直线需要同时满足满足：
 *
 * nums1[i] == nums2[j]
 * 且绘制的直线不与任何其他连线（非水平线）相交。
 * 请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。
 *
 * 以这种方法绘制线条，并返回可以绘制的最大连线数。
 *
 *
 * 这道题与 L1143 一模一样， 代码可以直接复制粘贴。 唯一区别就是一个是两个字符串， 一个是两个数组。
 *
 * 但是这道题引发了一个思考， 即， 以下代码是怎么保证 "线不想交的"？ 即对于这种问题而言， 他是怎么保证 所找出的 子数组 之中的元素相对顺序
 * 与 原数组 之中元素的相对顺序是不变的？
 *
 * 关键就在于， 他在比较相等的时候， "只比较了 t1, t2 两个当前指针指向的数。 并且 t1, t2 相等的情况下，dp[i][j] 是基于 dp[i-1][j-1] 计算的"。
 * 这代表什么？ 代表以下画面： 见sumarry。0
 *
 */
public class MaxLenOfRepeatedArraryNotContinious_L1035 {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {

                int t1 = nums1[i-1];
                int t2 = nums2[j-1];
                if (t1 == t2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[dp.length - 1][dp[0].length - 1];
    }
}
