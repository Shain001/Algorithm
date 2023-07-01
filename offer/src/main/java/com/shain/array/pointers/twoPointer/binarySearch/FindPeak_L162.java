package com.shain.array.pointers.twoPointer.binarySearch;

public class FindPeak_L162 {
    // 整体相当于通过二分找最大值
    // 只不过 这个题找的是"任意一个局部最大值" -> 比较 mid 与 mid+1 -> 前提是题目保证了一定有峰且没有相等元素
    // 回到这个题而言， 之所以能够用二分的关键是 条件说了  nums【-1】【len+1】 为负无穷
    // L852 山脉数组的峰顶索引 直接提交就能通过， 为什么？ L852 相当于题目保证了 整个数组 就是 这道题中的一个区间。即一定存在一个元素左侧都比他小， 右侧都比他大
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // 注意， 这里是与 mid+1 比， 而不是right， 相当于二分的作用仅仅是找到"某一个山的最高值"， 二分的作用是跨过爬山的阶段。
            if (nums[mid] < nums[mid + 1])
                left = mid + 1;
            else
                right = mid;
        }

        return left;
    }
}
