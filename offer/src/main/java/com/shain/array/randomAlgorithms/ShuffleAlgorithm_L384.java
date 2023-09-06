package com.shain.array.randomAlgorithms;

import java.util.Arrays;
import java.util.Random;

/**
 * todo: 补充随机性证明笔记/blog
 */
public class ShuffleAlgorithm_L384 {
    private int[] nums;
    private Random random;

    public ShuffleAlgorithm_L384(int[] nums) {
        this.random = new Random();
        this.nums = nums;
    }

    public int[] reset() {
        return this.nums;
    }

    public int[] shuffle() {
        int n = nums.length;
        int[] copy = Arrays.copyOf(nums, n);

        for (int i = 0; i < nums.length; i++) {
            int randomIndex = random.nextInt(i, nums.length);
            swap(copy, i, randomIndex);
        }

        return copy;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
