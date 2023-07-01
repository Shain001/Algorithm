package com.shain.binaryTree.backtrack;

import javax.swing.plaf.IconUIResource;
import java.util.Arrays;
import java.util.LinkedList;

public class PartitionKEqualSumSubsets_L698 {
    private int[] nums;
    private int k;
    private int target;
    private boolean result = false;
    private int[] bucket;

    public static void main(String[] args) {
        PartitionKEqualSumSubsets_L698 test = new PartitionKEqualSumSubsets_L698();
        System.out.println(test.canPartitionKSubsets(new int[]{4,3,2,3,5,2,1}, 4));
    }


    // 遍历桶， 让一个球 依次选择每个桶
    public boolean canPartitionKSubsets_v2(int[] nums, int k) {
        this.nums = nums;
        this.bucket = new int[k];
        this.k = k;

        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) {
            return result;
        }

        int left = 0, right= nums.length - 1;

        // 优化1： 将数组降序, 提高剪枝命中率
        Arrays.sort(nums);
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }


        this.target = sum/k;

        backTrack_v2(0);
        return result;
    }

    private void backTrack_v2(int curNum) {
        if (curNum == nums.length) {
            result = true;
            return;
        }

        for (int i = 0; i < k; i++) {
            int tempSum = bucket[i] + nums[curNum];

            // 两个相邻的桶想等时， 无需在将当前球放到当前桶
            if (i > 0 && bucket[i-1] == bucket[i]) {
                continue;
            }
            if (tempSum > target) {
                continue;
            }

            bucket[i] += nums[curNum];
            backTrack_v2(curNum+1);
            bucket[i] -= nums[curNum];
        }

    }

    // Your initial version, 超时，even after tried to prune
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
