package com.shain.array.pointers.twoPointer.binarySearch;

/*
    输入: nums = [1,3,5,6], target = 2
    输出: 1
 */
public class SearchInsert_L35 {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid])
                return mid;
            if (target < nums[mid])
                right = mid - 1;
            if (target > nums[mid])
                left = mid + 1;
        }

        // 之所以只需返回left即可， 是因为只要代码走到这， 说明nums中没有target
        // 而此时最后一次循环时的区间范围只有两种情况
        // 要么区间中只有两个数
        // 要么区间中有一个数
        // 而无论哪种情况， 最后一次循环一定会走到line 18
        // 假设区间中有两个数 【3， 5】, 由于 mid = left + (right-left)/2 是向下取整， 所以mid肯定指向3， 也即比target小的那个数， 所以
        // 在出while循环使， left = mid + 1 正好就是答案
        // 如果区间中只有一个数， 可以举个极限例子来理解
        // 假设 nums = [2] , target = 3 时，走入line18， 出while时left = 1 正好为答案
        // 假设 nums = [2], target = 1, 走入line16， right=-1， 出了while， left依然为0， 正好为答案。
        return left;
    }
}
