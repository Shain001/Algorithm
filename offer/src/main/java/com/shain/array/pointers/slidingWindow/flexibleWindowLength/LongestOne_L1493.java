package com.shain.array.pointers.slidingWindow.flexibleWindowLength;

public class LongestOne_L1493 {
    public int longestSubarray(int[] nums) {
        int idx = -1;
        int left = 0;
        int right = 0;
        int ans = 0;

        while (right < nums.length) {
            if (nums[right] == 0) {
                left = idx + 1;
                idx = right;
            }

            ans = Math.max(ans, right-left+1);
            right++;
        }

        return ans-1;
    }
}
