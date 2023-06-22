package com.shain.binaryTree.backtrack;

import javax.swing.plaf.IconUIResource;
import java.util.Arrays;
import java.util.LinkedList;

public class PartitionKEqualSumSubsets_L698 {
    int[] nums;
    int k;
    int target;
    boolean result = false;

    public static void main(String[] args) {
        PartitionKEqualSumSubsets_L698 test = new PartitionKEqualSumSubsets_L698();
        System.out.println(test.canPartitionKSubsets(new int[]{4,3,2,3,5,2,1}, 4));
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        this.nums = nums;
        this.k = k;

        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0)
            return false;

        this.target = sum / k;

        backTrack(0, 0, new boolean[nums.length]);
        return result;
    }

    private void backTrack(int count, int curSum, boolean[] used) {
        if (count == k) {
            result = true;
            return;
        }

        if (curSum - count * target > target)
            return;

        for (int i = 0; i < nums.length; i++) {
            if (used[i])
                continue;

            curSum += nums[i];
            used[i] = true;

            if (curSum % target == 0) {
                count += 1;
                backTrack(count, curSum, used);
                count -= 1;
            } else {
                backTrack(count, curSum, used);
            }

            curSum -= nums[i];
            used[i] = false;
        }
    }
}
