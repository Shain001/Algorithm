package com.shain.dataStructure.stackAndQueue;

import java.util.Deque;
import java.util.LinkedList;

public class Mode132_L456 {
    public static void main(String[] args) {
        var test = new Mode132_L456();
    }
    // 错误版本：
    // 错误原因： 维护两个数组， 丢失了 "顺序"
    // 即， 对于 min[i] 和 max[i] 而言。 max i 的元素是需要出现在 min i 之后的， 但是如下这种处理方法无法保证这一点。
    // 解决办法： 使用单调栈， 且只使用一个。
    public boolean find132pattern_wrong(int[] nums) {
        int[] min = new int[nums.length];
        int[] max = new int[nums.length];
        min[0] = nums[0];
        max[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            min[i] = Math.min(min[i-1], nums[i]);
            max[i] = Math.max(max[i-1], nums[i]);
        }

        for (int i = 2; i < nums.length; i++) {
            if (nums[i] > min[i-1] && nums[i] < max[i-1]) {
                return true;
            }
        }

        return false;
    }

    /**
     * 这题一个关键是理解 （以 遍历 132 中的3 而言） 假设 132 坐标为 i, j, k
     * 这个 3 左边的 2， 一定是尽可能的大， 即 贪心的思想
     * 同时， 3 一定是 当前坐标到末尾 的切片中 最大的元素。
     *
     * 而 stack 是单调递减， 进而保证：
     * 1。 当一个新的元素被遍历时， 在pop了stack以后， "能够快速检查 当前元素 的右侧是否有比他大的元素"
     * 2。 同时， stack 还保证了在pop的时候， 能够维护第二大元素。
     * @param nums
     * @return
     */
    public boolean find132pattern(int[] nums) {
        Deque<Integer> stack = new LinkedList<>();
        stack.addFirst(nums[nums.length-1]);
        int secondLargest = Integer.MIN_VALUE;

        for(int i = nums.length-2; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i] > stack.peekFirst()) {
                secondLargest = Math.max(stack.pollFirst(), secondLargest);
            }
            if (!stack.isEmpty() && nums[i] < secondLargest) {
                return true;
            }
            stack.addFirst(nums[i]);
        }

        return false;
    }
}
