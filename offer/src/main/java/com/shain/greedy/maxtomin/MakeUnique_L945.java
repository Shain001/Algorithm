package com.shain.greedy.maxtomin;

import java.util.Arrays;

public class MakeUnique_L945 {
    public int minIncrementForUnique(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        int i = 1;
        while (i < nums.length) {
            while (nums[i] <= nums[i-1]) {
                nums[i]++;
                ans++;
            }
            i++;
        }
        return ans;
    }

    // 计数排序
    public int minIncrementForUnique_v2(int[] nums) {
        final int MAX = 100000;

        int[] count = new int[MAX+1];
        for (Integer n : nums) {
            count[n]++;
        }

        int ans = 0;
        for (int i =0; i < MAX; i++) {
            if (count[i] > 1) {
                count[i+1] += count[i]-1;
                // 这里是 count[i]-1, 应为把 n 个数字都递增1， 移动了n次而不是1次。
                ans+= count[i]-1;
            }
        }

        if (count[MAX] > 1) {
            int temp = count[MAX] - 1;
            ans += (1 + temp) * temp / 2;
        }

        return ans;
    }

    // 线性探测法， 含路径压缩
    // 看题解： https://leetcode.cn/problems/minimum-increment-to-make-array-unique/solutions/163214/ji-shu-onxian-xing-tan-ce-fa-onpai-xu-onlogn-yi-ya/
    // nums 中数字， 以及数组长度范围都是[1, 10^5], 且只能对nums中元素进行递增操作，不能递减。
    // 所以假设 数组为 10^5 个 10^5, 那么右移以后为了得到无重复数字的数组， 数组长度为 2 倍的10^5, 所以 LEN 设置为 200000， 其表示长度
    private final int LEN = 200000;
    // pos数组中 pos[n] 的值表示的是 "以当前下标为起点的连续数字的结尾" 比如 pos[1] = 1, 就表示 1 这个数字已经有了， 结尾就在1。
    // pos[1] = 3 则表示， 1 这个数字已经有了， 并且 1， 2， 3 都有了， 即 pos[3] 这个格子也已经被占用了。
    // 所以 nextPos 应该等于 3+1 = 4， 即应该去看 pos[4] 有没有被占用。
    private int[] pos = new int[LEN+1];

    public int minIncrementForUnique_v3(int[] nums) {
        Arrays.fill(pos, -1);
        int ans = 0;

        for (Integer n : nums) {
            int insertedPos = insert(n);
            ans += insertedPos - n;
        }

        return ans;
    }

    private int insert(int n) {
        if (pos[n] == -1) {
            pos[n] = n;
            return n;
        }

        int nextPos = pos[n] + 1;
        int inserted = insert(nextPos);
        pos[n] = inserted; // 路径压缩的关键
        return inserted;
    }
}
