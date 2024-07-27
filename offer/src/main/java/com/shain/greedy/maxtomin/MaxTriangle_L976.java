package com.shain.greedy.maxtomin;

import java.util.Arrays;

public class MaxTriangle_L976 {
    /**
     * 证明：
     * 贪心策略的局部最优性：
     *
     * 从数组的最后开始（即从最大的边开始），尝试使用最大的三条边构成三角形。如果能够构成，则这三个边的周长最大。
     * 如果最大的三条边不能构成三角形，则尝试次大的三条边。由于数组已经排序，这样的尝试不会遗漏任何可能性。
     *
     * 贪心策略的全局最优性：
     *
     * 设 nums[i]、nums[i-1]、nums[i-2]是最大的三条边，如果它们不能构成三角形（即 nums[i-2] + nums[i-1] <= nums[i]），则任何包含 nums[i] 的三角形都不会比这个三角形周长更大，因为 nums[i] 是最大的边。
     * 因此，放弃最大的边 nums[i]，尝试次大的三条边是合理的，并且能够保证找到的三角形周长是最大的。
     * @param nums
     * @return
     */
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        int i = nums.length-1;
        while (i >= 2) {
            int curMax = nums[i];
            int j = i-1;
            int k = i-2;
            if (nums[j] + nums[k] > curMax) {
                return nums[j] + nums[k] + nums[i];
            }
            i--;
        }
        return 0;
    }
}
