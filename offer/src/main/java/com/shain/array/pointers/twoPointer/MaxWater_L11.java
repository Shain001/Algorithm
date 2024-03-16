package com.shain.array.pointers.twoPointer;

public class MaxWater_L11 {
    public int maxArea(int[] nums) {
        int left = 0;
        int right = nums.length-1;
        int ans = 0;

        while (left < right) {
            int l = right - left;
            ans = Math.max(l * (Math.min(nums[left], nums[right])), ans);

            if (nums[left] < nums[right]) {
                left++;
            } else {
                right--;
            }
        }

        return ans;
    }
}
