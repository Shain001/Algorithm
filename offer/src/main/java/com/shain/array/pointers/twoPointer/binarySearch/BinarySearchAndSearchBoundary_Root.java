package com.shain.array.pointers.twoPointer.binarySearch;

// 只要不是在while中直接return的情况， 都适合用 while (left < right)
// p.s. 所谓适合， 只是为了使 代码更clean

// right 永远等与 nums.length-1
// while 中取等号与不取等号在任何情况下， 都是可以的
// 唯一区别在于：
// 只要 while 中不取等号，必定会漏判到一个区间， 即 [left, right] where left = right （right=nums.length-1时， 不确定right=nums.length时是否适用该结论）
// 该漏判的结论， 依然适用于 searchLeft/ searchRight 方法，
// 但是 巧合的地方在于， searchLeft / searchRight 方法中需要判断 target是否在nums中， 所以 就算使 while条件加上=， 其依然需要在while 外
// 加一个if条件， 所以没有必要使其while 中加上=
// 换句话说， searchLeft / search Right 的while 外的if 判断做了两件事：
// 1. 判断 target是否在nums中， 如果不在， 返回-1
// 2. 判断漏判的 [left, right] where left = right 区间里的数是否等于 target。

// Moreover,
// 对于当 while 中存在 left = mid; 时， 需要使 mid = left + (right-left+1)/2; 的问题
// 其原因在于 当只有区间中只剩俩个数时， mid=left+(right-left)/2 永远只能取到区间中第一个数， 导致left 无法更新，而right又一直不变， 所以left 永远无法等于right， 造成死循环。
// 而 对于 right = mid 的条件， 当只剩两个数时， 由于是向下取整， right会被取到 等于 left 的那一个index， 所以正好能够出循环， 不会导致死循环。
public class BinarySearchAndSearchBoundary_Root {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 3, 5, 9, 12};

        int[] test1 = new int[]{1, 2, 2, 2, 2, 2, 9};
        int[] test2 = new int[]{1, 2};
        int[] test3 = new int[]{1};

        System.out.println(searchRightBoundary(test1, 9));
    }

    public static int searchLeftBoundary(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target < nums[mid])
                right = mid - 1;
            if (target > nums[mid])
                left = mid + 1;
            if (target == nums[mid])
                right = mid;
        }

        // This if does two things
        // 1. check if the targeted value exists in nums
        // 2. check 漏判的 left = right 时的 [left, right] 区间中的数是否等于target
        if (nums[left] != target) return -1;

        return left;
    }

    // 只要不是在while中直接return的情况， 都是 while (left < right)
    public static int searchRightBoundary(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        // < 的好处为最终while一定停止在left=right， 无需判断返回left还是right。
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (target < nums[mid])
                right = mid - 1;
            if (target > nums[mid])
                left = mid + 1;
            if (target == nums[mid])
                left = mid;

        }

        if (nums[left] != target) return -1;
        return left;
    }

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            // Point 3:
            // 注意， 此处永远是向下取整， 即当list长度为偶数， mid有两个时， 取得的永远是左侧的mid。
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] < target) {
                left = mid + 1;
                continue;
            }

            right = mid - 1;
        }
        return -1;
    }

}
