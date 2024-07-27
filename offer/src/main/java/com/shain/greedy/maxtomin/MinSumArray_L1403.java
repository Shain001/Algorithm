package com.shain.greedy.maxtomin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinSumArray_L1403 {
    public List<Integer> minSubsequence(int[] nums) {
        Arrays.sort(nums);
        List<Integer> ans = new ArrayList<>();
        int sum = 0;
        int curSum = 0;
        for (Integer n : nums) {
            sum += n;
        }
        int i = nums.length-1;
        while (curSum <= sum && i >= 0) {
            curSum += nums[i];
            sum -= nums[i];
            ans.add(nums[i--]);
        }

        return ans;
    }
}
