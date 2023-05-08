package com.shain.array.pointers.twoPointer.binarySearch.rotateArray;

public class SearchRotateArray_L33 {
    public static void main(String[] args) {

    }

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) - 1;
            if (nums[mid] == target)
                return mid;

            // mid 小于， left 小于 -> 右
            // mid 小于， left 大于 -> 右
            // mid 大于， left 大于 -> 右
            // mid 大于， left 小于 -> 左
            if (nums[mid] < target) {
                if (nums[left] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {

            }
        }

        return -1;
    }
}
