package com.shain.array.swap;

/**
 * elements from 0 to n-1
 * length of array n
 * find repeat
 *
 * @date: 04/10/2022
 */
public class FindRepeatNumber_1 {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 0, 2, 5, 3};
        System.out.println(findRepeatReview(nums));
    }

    /**
     * 19/02/2023
     */
    public static int findRepeatReview(int[] nums) {

        for (int i = 0; i < nums.length; i++) {

            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]])
                    return nums[i];
                int temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;
            }
        }

        return -1;
    }

    private static void doSwap(int curIndex, int curVal) {

    }
}
