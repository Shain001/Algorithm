package com.shain.array.pointers.twoPointer.nSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public static void main(String[] args) {
        int[] test = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(test));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List result = new ArrayList();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            // 避免重复解 e.g. [-1,0,1,2,-1,-4]
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            int targetOfTwo = 0 - nums[i];
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int curSum = nums[left] + nums[right];
                if (curSum == targetOfTwo) {
                    result.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));
                    left++;
                    // 避免重复解， e.g. [0,0,0,0,0]
                    while (left < right && nums[left] == nums[left - 1])
                        left++;
                } else if (curSum < targetOfTwo)
                    left++;
                else
                    right--;
            }

        }
        return result;
    }
}
