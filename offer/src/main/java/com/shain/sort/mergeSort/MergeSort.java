package com.shain.sort.mergeSort;

import java.util.Arrays;

public class MergeSort {
    private int[] nums;

    public static void main(String[] args) {
        MergeSort test = new MergeSort();
        System.out.println(Arrays.toString(test.mergeSort(new int[]{2})));
    }

    public int[] mergeSort(int[] nums) {
        this.nums = nums;
        return doMergeSort(0, nums.length - 1);
    }

    private int[] doMergeSort(int left, int right) {
        if (left == right) {
            return new int[]{nums[left]};
        }

        int mid = left + (right - left) / 2;
        return merge(doMergeSort(left, mid), doMergeSort(mid + 1, right));
    }

    private int[] merge(int[] left, int[] right) {
        int[] merged = new int[left.length + right.length];

        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                merged[k++] = left[i++];
            } else {
                merged[k++] = right[j++];
            }
        }

        while (i < left.length) {
            merged[k++] = left[i++];
        }

        while (j < right.length) {
            merged[k++] = right[j++];
        }

        return merged;
    }
}
