package com.shain.array.findRepeatNumber.singleNumberSeries;

/**
 * 异或 的特性：
 * 任何数和 0 做异或运算，结果仍然是原来的数
 * 任何数和其自身做异或运算，结果是 0
 * 异或运算满足交换律和结合律
 * x ^ 1 = ~x 按位取反运算符翻转操作数的每一位，即0变成1，1变成0。
 */
public class SingleNumber1_L136 {
    public int singleNumber(int[] nums) {
        int result = 0;

        for (Integer n: nums) {
            result ^= n;
        }

        return result;
    }
}
