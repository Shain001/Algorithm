package com.shain.array.pointers;

import java.util.Deque;
import java.util.LinkedList;

public class MaxRetc_L84 {
    public static void main(String[] args) {
        var test = new MaxRetc_L84();
        System.out.println(test.largestRectangleArea(new int[]{2,1,5,6,2,3}));
    }
    public int largestRectangleArea(int[] heights) {
        if (heights.length == 1) {
            return heights[0];
        }

        int ans = 0;
        // first element's index that smaller than h[i].
        // if h[i] is the smalles, store itself
        int[] left = new int[heights.length];
        int[] right = new int[heights.length];
        Deque<Integer> stack = new LinkedList<>();

        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }

            // 找到 小于当前 柱子的 第一个元素的坐标
            // 如果栈是空， 说明当前坐标在 [0-i] 之间最小， 则left[i] = -1 作为标记
            left[i] = stack.isEmpty()? -1: stack.peek();
            // 当前元素入栈， 实现 递增栈。
            // 递增栈的意义在于 能够加速 上面while的过程， 即加速 每一个元素找到第一个小于自己的元素
            stack.push(i);
        }

        // 处理right数组， 找到 每个元素 右边小于他的第一个元素。
        stack.clear();
        for (int i = heights.length-1; i >= 0 ; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }

            right[i] = stack.isEmpty()? heights.length: stack.peek();
            stack.push(i);
        }

        for (int i = 0; i < heights.length; i++) {
            int h = heights[i];

            ans = Math.max(ans, h * (right[i] - left[i] - 1));
        }


        return ans;
    }
}
