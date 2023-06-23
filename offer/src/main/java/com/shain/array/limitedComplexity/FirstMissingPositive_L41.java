package com.shain.array.limitedComplexity;

public class FirstMissingPositive_L41 {
    public int firstMissingPositive(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            // wrong before: missed condition nums[nums[i]-1] != nums[i]. 因为数组中可能包含重复元素， e.g. 在 [1, 1]这种情况下，
            // 不加该判断会死循环。 加了以后的意义就是： 如果要交换的两个元素是相等的， 并且这个值已经不应该在当前i上了， 那么换了也是白换， 并且会死循环。
            // todo: 加了 nums[nums[i]-1] != nums[i] 以后貌似就不需要 i != nums[i]-1 的条件了。 但是没仔细想为什么。
            while (nums[i] <= nums.length && nums[i]>0 && i != nums[i]-1 && nums[nums[i]-1] != nums[i]) {
                doSwap(nums, i, nums[i]-1);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i+1)
                return i+1;
        }

        return nums.length+1;
    }

    private void doSwap(int[] nums, int i, int num) {
        int temp = nums[num];
        nums[num] = nums[i];
        nums[i] = temp;
    }
}
