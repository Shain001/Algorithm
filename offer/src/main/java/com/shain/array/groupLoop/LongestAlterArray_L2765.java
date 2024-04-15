package com.shain.array.groupLoop;

public class LongestAlterArray_L2765 {
    public int alternatingSubarray(int[] nums) {
        int i = 1;
        int ans = 0;

        while (i < nums.length) {
            if (nums[i] - nums[i-1] != 1) {
                i++;
                continue;
            }

            // 当前分组起始于 i-1.
            // i 与 i-1 差值为 1， 则当前分组 在这两个数组以后 多应该是 不断重复的一个数对
            int j = i-1;
            // 目前i为分组的第二为， 要+1 使其变到下一位继续检查。
            i += 1;
            while (i < nums.length && nums[i] == nums[i-2]) {
                i++;
            }

            ans = Math.max(i - j, ans);
        }

        return ans == 0? -1: ans;
    }
}
