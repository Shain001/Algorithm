package com.shain.array.prefixSum;

public class RangeSumQuery_L303 {
    private final int[] prefixSum;


    // 前缀和， 即用一个数组存储 从0-i位 的所有数的和
    // 作用为方便多次计算各个区间内的数字和， 将其时间复杂度降为 O(1)
    public RangeSumQuery_L303(int[] nums) {
        // 长度应为length+1
        // 因为 计算0-1 区间内的数时， 其前缀和为 prefixSum[1] - prefixSum[0]
        // 或者可以理解为 对于区间 0-0， 其和应为0
        // NOTE: 此种写法为 "左开右闭区间"， 因此， 返回 原数组中 0-4 下标内的数时， 即为 prefix [5] (对应前4位的和)- prefix[0]
        // 如果初始化prefix长度=length， 则对于0-4 的区间时， 0不好处理
        prefixSum = new int[nums.length+1];
        prefixSum[0] = 0;

        // 初始化前缀和数组
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i+1] = prefixSum[i] + nums[i];
        }
    }

    // 具体返回 prefixSum[right] - prefix[left]
    // 还是 prefixSum[right-1]-xxxxx
    public int sumRange(int left, int right) {
        return prefixSum[right+1]-prefixSum[left];
    }
}
