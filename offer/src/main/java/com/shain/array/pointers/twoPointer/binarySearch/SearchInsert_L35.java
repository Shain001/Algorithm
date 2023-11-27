package com.shain.array.pointers.twoPointer.binarySearch;

/*
    输入: nums = [1,3,5,6], target = 2
    输出: 1

    update 2023/11/26:
    这题 之所以需要加 =， 并且在while中直接返回， 并且可以直接return left， 因为:
    1. target 有可能在数组中。 所以可以直接return， 应为可以直接return了， 那么自然就要left=right为循环条件， 否则while中会漏判。
    2. 也是因为target有可能在数组中， 所以你不能想着 不在while中return， 因为就这题而言， 当target在数组中时， return的是target的index。
       若不在的话， return的应该是 出循环时 left指针指向的index+1， see？ 两者return的不一样
    3. 进而在while循环条件中 加了 = 以后， 恰好出while循环以后 left与right指针错开一位， left正好指向+1的位置， 所以直接return left即可。

 */
public class SearchInsert_L35 {
    public static void main(String[] args) {
        var test = new SearchInsert_L35();

        System.out.println(test.searchInsert(new int[]{2,2}, 3));
    }
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
