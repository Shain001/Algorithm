package com.shain.array.pointers.twoPointer.binarySearch.rotateArray;

/**
 * 难点在于分析， 一定要画图， 分析所有情况。
 *
 * 先确定 mid 在 左边上升区间， 还是右边上升区间。
 * 然后判断 target > mid 还是 < mid。
 * 一一分情况讨论即可。
 *
 * 还需注意以下 由于 mid = left + (right-left)/2; 导致的 mid = left 的情况。
 */
public class SearchRotateArray_L33 {
    public static void main(String[] args) {

    }

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;

        while (left <= right) {
            int mid = left + (right-left)/2;

            if (nums[mid] == target) {
                return mid;
            }

            // m 在左
            if (nums[mid] >= nums[left]) {
                if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    if (target >= nums[left]) {
                        right = mid-1;
                    } else {
                        left = mid+1;
                    }
                }
            } else {
                if (nums[mid] < target) {
                    if (target <= nums[right]) {
                        left = mid+1;
                    } else {
                        right = mid-1;
                    }
                } else {
                    right = mid-1;
                }
            }

        }
        return -1;
    }
}
