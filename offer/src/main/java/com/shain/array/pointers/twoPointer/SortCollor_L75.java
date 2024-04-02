package com.shain.array.pointers.twoPointer;

public class SortCollor_L75 {
    public void sortColors(int[] nums) {
        int p0 = 0;
        int p2 = nums.length-1;
        int i = 0;

        while (i <= p2) {
            while (i <= p2 && nums[i] == 2) {
                swap(nums, i, p2);
                p2--;
            }

            if (nums[i] == 0) {
                swap(nums, i, p0);
                p0++;
            }


            i++;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
