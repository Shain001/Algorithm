package com.shain.array.pointers.twoPointer.binarySearch.rotateArray;

public class FindMinInRotatedArray_L153 {
    public static void main(String[] args) {
        int[] test = new int[] {3,1,2};
        System.out.println(findMin(test));
    }

    public static int findMin(int[] nums) {
        if (nums.length == 0)
            return -1;

        int left = 0;
        int right = nums.length - 1;

        // 此处如果加了等于号， 会出现死循环
        // case: [3,4,5,1,2]
        // why? 因为最后区间内会只剩下两个元素， 而此时， mid会永远落在left；
        // nums[mid] 又小于 nums[right]， 即进入line 24， 使得left=right， 无法出循环。
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right])
                left = mid+1;
            if (nums[mid] < nums[right])
                right = mid;
        }

        return nums[left];
    }
}
