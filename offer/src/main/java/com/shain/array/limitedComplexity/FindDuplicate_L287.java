package com.shain.array.limitedComplexity;

/**
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
 * <p>
 * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
 * <p>
 * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
 * <p>
 * method1:
 * 二分。 由于数组长度为 n+1，但是 数字范围为 1-n， 所以必定至少有一个元素重复。
 * 又因为 元素取之范围 与 下标范围可对应， 并且 "数组中的元素虽然无序， 但是数组的下标是有序的"， 所以可以找通过下标寻找答案。
 * <p>
 * method2：
 * 转换为 找环形链表环的入口。
 *
 *
 * 如何证明 满足这种条件的 数组一定有环？
 * 我们对 nums 数组建图，每个位置 i 连一条 i→nums[i]的边。由于存在的重复的数字 target，因此 target 这个位置一定有起码两条指向它的边，因此整张图一定存在环。
 *
 */
public class FindDuplicate_L287 {
    public static void main(String[] args) {
        var test = new FindDuplicate_L287();
        System.out.println(test.method2(new int[]{3, 3, 3, 2, 2}));
    }

    public int findDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            int countMid = doCount(nums, mid);

            // 假设 mid=4， 那么在数组中， 小于等于4的值在没有重复的情况下应该是4个。
            // 那么假设这个count大于了4个， 说明 在 1-4 这个范围内必定有重复的数字， 所以我们需要在 1-4 的范围查找， 也即找左边的区间。
            if (countMid > mid) {
                right = mid;
            } else
                left = mid + 1;

        }
        return left;
    }

    private int doCount(int[] nums, int mid) {
        int count = 0;

        for (int i = 0; i <= nums.length - 1; i++) {
            count = nums[i] <= mid ? count + 1 : count;
        }

        return count;
    }

    /**
     * 将 元素值看作指针， 指向下一个节点的下标。
     * <p>
     * 这种方法有2个必要前提条件， 即：
     * 1. 元素的取之范围必须 小于 nums.len， 否则会空指针报错
     * 2. 必须满足 "最多只有一个重复元素"，但是这个重复元素的出现次数不限。
     * e.g. [2, 5, 3, 1, 3, 5] 不行， 因为这个数组实际组成了多个环
     * [2, 5, 3, 3, 3, 4] 可以， 因为尽管3出现了more than 2 times， 还是只有一个环。
     * <p>
     * <p>
     * 换句话说， 只要满足元素 取之范围属于 [1, n] && length = n+1，并且 只有一个重复元素， 则可以使用该算法。
     * 同时， 如果满足以上条件， 该方法也可用于判断是否有重复元素， 无需返回。 即变成了判断环形链表，而不是找入口。
     * <p>
     * 为什么 元素取值范围属于 0-n 不行？ 因为 有0 的话， 可能出现 self-loop的情况， slow pointer可能在进入环之前进入这个self-loop
     *
     * @param nums
     * @return
     */
    public int method2(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]];

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        fast = 0;

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;

    }


}
