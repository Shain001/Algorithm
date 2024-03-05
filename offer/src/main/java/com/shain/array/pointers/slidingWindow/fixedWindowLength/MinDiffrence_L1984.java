package com.shain.array.pointers.slidingWindow.fixedWindowLength;

import java.util.Arrays;

public class MinDiffrence_L1984 {
    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);

        int left = 0;
        int right = k-1;
        int result = nums[right] - nums[left];

        while (right < nums.length) {
            result = Math.min(nums[right]-nums[left], result);
            right++;
            left++;
        }

        return result;
    }
}
