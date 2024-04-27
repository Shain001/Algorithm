package com.shain.dataStructure.stackAndQueue;

import java.util.Deque;
import java.util.LinkedList;

public class MaximumRetangular_L85 {
    public int maximalRectangle(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] heights = new int[cols];
        int[] right = new int[cols];
        int[] left = new int[cols];
        Deque<Integer> stack = new LinkedList<>();
        int ans = 0;

        for (int i = 0; i < rows; i++) {
            stack.clear();

            // update heights and left;
            for (int j = 0; j < cols; j++) {

                if (matrix[i][j] == '0') {
                    heights[j] = 0;
                } else {
                    heights[j] = 1 + heights[j];
                }

                while (!stack.isEmpty() && heights[stack.peek()] >= heights[j]) {
                    stack.pollFirst();
                }

                left[j] = stack.isEmpty() ? -1 : stack.peekFirst();
                stack.offerFirst(j);
            }

            stack.clear();
            // update right and calculate areas
            for (int j = cols - 1; j >= 0; j--) {
                while (!stack.isEmpty() && heights[stack.peek()] >= heights[j]) {
                    stack.pollFirst();
                }

                right[j] = stack.isEmpty() ? cols : stack.peekFirst();
                stack.offerFirst(j);

                ans = Math.max(heights[j] * (right[j] - left[j] - 1), ans);
            }
        }

        return ans;
    }
}
