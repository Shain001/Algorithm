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

        // 之所以只需返回left即可， 是因为:
        // 首先如果代码走到这里， 说明没有target。
        // 其次， you would be wondering, why left is always the correct answer, if so, always take two examples
        // first， 数组长度为2 [3, 5] 搜索 target=1。
        // 由于 mid = left + (right - left) / 2; 总是向下取整 （这里很重要）， 所以第一次循环 比的是left （因为mid计算后就等于0）
        // 此时 target < mid， "left 不用动"， right=mid-1， 之后right=left， target依然小于mid， left还是不动， 第二次循环以后
        // 就出while了。 see, left is always not moving.
        // Now， let's make target = 6， it would be similar, you can try.
        // Then you give an example as [4] and target = 1 or 5, that would be the same
        return left;
    }
}
