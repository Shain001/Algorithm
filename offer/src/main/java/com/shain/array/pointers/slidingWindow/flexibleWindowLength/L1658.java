package com.shain.array.pointers.slidingWindow.flexibleWindowLength;

public class L1658 {
    public int minOperations(int[] nums, int x) {
        int sum = 0;
        for (Integer n : nums) {
            sum += n;
        }
        int target = sum-x;

        if (target < 0) {
            return -1;
        }

        if (sum == x) {
            return nums.length;
        }

        int right = 0;
        int left = 0;
        int ans = 0;
        int curSum = 0;

        while (right < nums.length) {
            curSum += nums[right];

            while (curSum > target) {
                curSum -= nums[left++];
            }

            // 注意这个if的位置， 不能写在 while之前， 否则可能漏判。
            if (curSum == target) {
                ans = Math.max(ans, right-left+1);
            }

            right++;
        }

        return ans == 0? -1: nums.length-ans;
    }
}
