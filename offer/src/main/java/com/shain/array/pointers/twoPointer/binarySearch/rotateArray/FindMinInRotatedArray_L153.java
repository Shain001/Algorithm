package com.shain.array.pointers.twoPointer.binarySearch.rotateArray;

/**
 * 这题其中一个点是。 原本数组是有序的， 这代表着 nums[mid] 是不可能大于nums[right] 的。
 * 所以， 只要 二分过程中发现了   nums[mid] 小于[right]， 那么说明 右边区间可以直接抛弃， 这个毫无疑问。
 *
 * 若 发现mid 大于 right， 那么说明 最小值肯定在 mid 和 right 之间。 注意这个很重要。 可以看一下官方题解的图如果忘了的话。
 *
 * 而确定了 最小值所在这个区间以后， 由于 我们知道 最小值 一定在 mid right之间了， 那么自然可以直接抛弃 left-mid， 使得left = mid+1。
 *
 *
 * 题解中写的 left+= 1， 不用看， 误导人， 直接left = mid+1 是可以的
 *
 * 二如果
 */
public class FindMinInRotatedArray_L153 {
    public static void main(String[] args) {
        int[] test = new int[]{3, 4, 5, 1, 2};
        System.out.println(findMin(test));
    }

    public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length -1;

        while (left < right) {
            int mid = left + (right-left)/2;

            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid+1;
            }
        }

        return nums[left];
    }
}
