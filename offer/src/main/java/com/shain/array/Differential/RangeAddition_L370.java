package com.shain.array.Differential;

/**
 * 1. Def
 * 差分即维护一个差分数组，其第i位表示 原数组中第i位与第i-1位的 "差值" （i ≠ 0）。数组0位为原数组中第0位的值。
 * <p>
 * <p>
 * 2. Meaning
 * 其作用 以O(1)的时间复杂度， 对某一个index 属于 [i, j] 内的元素统一进行 增减 运算。
 * 所以适用于需要频繁的对某区间内的数进行统一加减的情况。
 * 当批量增减完成， 即可通过第0位数组的值， 推算出所有元素的最终值。
 * NOTE: 所谓O(1), 只是对于批量增减环节而言， 对于创建差分数组， 以及还原原数组的过程复杂度仍然为 O(n)。
 * <p>
 * 3. Addition Process
 * 要对[i, j] 区间内的数进行统一的增加x (x 可为负)， 则须对 diff[i] + x, 同时对 diff[j] -x.
 * 因为如果不对 diff[j] 进行-x 的操作， 那么 j以后的元素也将被 +x
 * <p>
 * 4. Differential VS Prefix Sum
 * 其与前缀和不同的第一个地方在于， 前缀和第i位表示的是"前i位的和"， 而差分数组中第i位仅表示"当前位与前一位'两个位'之间的差"。
 * 第二个不同在于， prefix sum 中的 prefix 数组长度为 nums.length + 1, 因为这样更方便计算从0开始的区间。
 * 但diff 长度为 nums.length, 因为没有必要对区间0特殊处理。
 */
public class RangeAddition_L370 {
    private int[] diff;

    public RangeAddition_L370(int[] nums) {
        diff = new int[nums.length];
        diff[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            diff[i] = nums[i] - nums[i - 1];
        }
    }

    public void increment(int x, int left, int right) {
        diff[left] += x;
        // NOTE: 由于right可能等于nums.length-1， 即数组的右边界， 所以需要判断。
        // 即， 如果是数组中最后一个元素， 则无需在考虑对其之后的元素的影响， 因为没有后面的元素了。
        if (right < diff.length)
            diff[right] -= x;
    }

    // 还原数组
    public int[] result() {
        int[] afterAddition = new int[diff.length];
        afterAddition[0] = diff[0];

        for (int i = 1; i < diff.length; i++) {
            afterAddition[i] = afterAddition[i - 1] + diff[i];
        }

        return afterAddition;
    }


}
