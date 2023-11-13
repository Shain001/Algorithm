package com.shain.array.findRepeatNumber.swapAndIndexRelated;

/**
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
 * <p>
 * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
 * <p>
 * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
 * <p>
 * method1:
 * 二分。 由于数组长度为 n+1，但是 数字范围为 1-n， 所以必定至少有一个元素重复。
 *
 * 那么，既然知道了 元素取值范围 为 1 - n， 那么正常情况下 我们遍历 1-n， 看看数组中 哪个数字是重复的即可得到答案。
 * 即， 利用暴力解法就可以得到答案。
 * 所谓二分方法， 仅仅是对暴力解法的一种优化而已。 即 instead of 遍历 1-n 中所有取值， 我们利用二分来遍历。
 * 即 二分的是 元素的取值， ！与下标无关
 * 进而， 当 mid = 2 时。 数组中 <=2 的元素应该只有2 个， 即 1&2， 而如果 遍历数组以后， 发现 <=2 的元素 大于2个。 那么 1与2 中则必定有一个
 * 重复的， 进而我们知道 新的搜索区间应该是  left - mid.
 *
 * <p>
 * method2：
 * 转换为 找环形链表环的入口。
 * <p>
 * <p>
 * 如何证明 满足这种条件的 数组一定有环？
 * 我们对 nums 数组建图，每个位置 i 连一条 i→nums[i]的边。由于存在的重复的数字 target，因此 target 这个位置一定有起码两条指向它的边，因此整张图一定存在环。
 */
public class FindDuplicate_L287 {
    public static void main(String[] args) {
        var test = new FindDuplicate_L287();
        System.out.println(test.method2(new int[]{3, 3, 3, 2, 2}));
    }

    private int[] nums;

    public int findDuplicate(int[] nums) {
        this.nums = nums;
        // 注意， left 和 right， 是 元素的取值范围
        // 对于一个长度为n的数组。 由于其 元素取值范围是 【1，n-1】， 所以这样定义 left与right的初始值
        // 即重点在于理解， 我们二分的是 元素的值， 与数组下标无关！
        // 或者说， 二分仅仅是对于暴力循环解法的优化。 暴力解时， 我们一次循环 元素 1,2,3,....
        // 但是利用二分， 我们可以提高时间复杂度。
        // 其能够实现的原因在于： 如果 left - mid 这个范围中没有重复数字， 那么 "整个数组中" 小于等于 mid 的元素个数一定是 "不大于" mid 的
        // 注意这里一定是不大于， 而不是等于， 即 一定是 if(count>mid) right=mid; 因为 有 [2,2,2,2,2] 这种情况。
        int left = 1;
        int right = nums.length-1;

        while (left < right) {
            int mid = left + (right-left) / 2;
            int count = doCount(mid);

            if (count > mid) {
                right = mid;
            } else {
                left = mid+1;
            }
        }

        return left;
    }

    private int doCount(int n) {
        int count = 0;
        for (int num : nums) {
            count = num <= n ? count + 1 : count;
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
