package com.shain.array.groupLoop;

import java.util.Arrays;

public class MinOperation_L1887 {
    public static void main(String[] args) {
        var test = new MinOperation_L1887();
        System.out.println(test.reductionOperations(new int[]{1,1,2,2,3}));
    }
    public int reductionOperations(int[] nums) {
        Arrays.sort(nums);

        for (int i = 0; i < nums.length / 2; i++) {
            int temp = nums[i];
            nums[i] = nums[nums.length - 1 - i];
            nums[nums.length - 1 - i] = temp;
        }

        int i = 0;
        int ans = 0;
        while (i < nums.length) {
            while (i >= 1 && i < nums.length && nums[i] == nums[i-1]) {
                i++;
            }

            if (i != nums.length)
                ans += i;
            i++;
        }

        return ans;
    }
}
