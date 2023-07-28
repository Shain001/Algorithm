package com.shain.dp.array.subArray;

import java.util.Arrays;

/**
 * Target result is "not continuous -> dp array should mean 'x-i' -> a note from @Leihehe".
 */
public class LongestIncreasingSubseq_L300 {
    public static void main(String[] args) {
        var test = new LongestIncreasingSubseq_L300();
        System.out.println(test.lengthOfLIS_binarySearchAndGreedy(new int[]{10,9,2,5,3,7,101,18}));
    }

    /**
     * 可以二分查找优化 内循环的for。 要用额外空间。 没记在这。
     *
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int result = 1;
        for (int i = 1; i < nums.length; i++) {
            // missed before and caused mistake
            int temp = 1;

            for (int j = i; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    temp = Math.max(dp[j] + 1, temp);
                }
            }

            dp[i] = temp;
            result = Math.max(result, dp[i]);
        }

        // update: 对于这种数组相关的题， 一定要注意 dp数组的含义。 该题dp数组含义是 从 下标x 到 i 位置的最大严格上升子序列长度， 而不是 从0-i。
        // 所以一定要维护 result， 记录最大值。 你经常写成直接返回 dp[-1]
        return result;
    }

    // review 27/07/2023
    public static int lengthOfLIS_review(int[] nums) {
        if (nums == null) {
            return -1;
        }

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        // wrong2: missed
        int result = 1;

        for (int i = 1; i < nums.length; i++) {
            // wrong 1: you would have to compare every previous smaller values
//            if (nums[i] > nums[i-1]){
//                dp[i] = dp[i-1] + 1;
//                continue;
//            }
            int j = i - 1;

            while (j >= 0) {
                if (nums[j] < nums[i])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                j--;
            }
            result = Math.max(result, dp[i]);
        }

        return result;
    }

    private int len;
    private int[] tails;

    /**
     * check blog
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS_binarySearchAndGreedy(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;

        tails = new int[nums.length + 1];
        tails[1] = nums[0];
        this.len = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > tails[len]) {
                tails[++len] = nums[i];
                continue;
            }

            int index = getIndex(nums[i]);
            tails[index] = nums[i];
        }

        return len;
    }

    private int getIndex(int target) {
        int left = 1;
        int right = this.len;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (tails[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
