package com.shain.sort.quickSort;

public class QuickSort {
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length-1);
        return nums;
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivot = getPivot(nums, left, right);
        quickSort(nums, left, pivot-1);
        quickSort(nums, pivot+1, right);
    }

    private int getPivot(int[] nums, int left, int right) {
        int pivot = nums[left];
        int lt = left;  // 小于 pivot 的指针
        int gt = right;  // 大于 pivot 的指针
        int i = left;  // 当前遍历的指针

        while (i <= gt) {
            if (nums[i] < pivot) {
                swap(nums, i, lt);
                i++;
                lt++;
            } else if (nums[i] > pivot) {
                swap(nums, i, gt);
                gt--;
            } else {
                i++;
            }
        }

        return lt;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
