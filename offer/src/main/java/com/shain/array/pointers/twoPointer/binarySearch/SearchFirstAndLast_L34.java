package com.shain.array.pointers.twoPointer.binarySearch;

public class SearchFirstAndLast_L34 {
    public static void main(String[] args) {
        var test = new SearchFirstAndLast_L34();
        System.out.println(test.searchRange(new int[]{2,2}, 3));
    }
    public int[] searchRange(int[] nums, int target) {
        if (nums.length==0) {
            return new int[]{-1, -1};
        }
        int[] result = new int[2];

        result[0] = searchLeft(nums, target);
        result[1] = searchRight(nums, target);

        return result;
    }

    public int searchLeft(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;

        while (left <right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < target) {
                left = mid+1;
            } else if (nums[mid] > target) {
                right = mid-1;
            } else {
                right = mid;
            }
        }

        // 注意这里的return 和 searchRight 的return中 对于 nums[xx] 的判断
        // 此处需要判断 nums[left] 不可以是num[right]， 因为 nums[right] 可能会超界。
        // 之所以有这样的例子， 是因为 function 中有：
        // else {
        //                right = mid; ｜ left = mid
        // 这样的写法导致， 如果target不再nums中， 在搜索左边界时， right 会一直往左移， 最终指向index=-1， 所以此处的return不可以判断
        // nums[right]， 但是如果target在nums中， 那么left right 会在出while以后指向同一个index。
        // 而 就算target 不再nums中， left指针也不可能会超界。 同理， 在searchRight时 right也不可能会超界。
        // e.g. [2,2] target=1 -> 搜索左边界， right 会一直向左移动， 最终指向-1。
        return nums[left] == target? left:-1;
    }

    public int searchRight(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;

        while (left <right) {
            int mid = left + (right - left+1) / 2;

            if (nums[mid] < target) {
                left = mid+1;
            } else if (nums[mid] > target) {
                right = mid-1;
            } else {
                left = mid;
            }
        }
        return nums[right] == target? right:-1;
    }
}
