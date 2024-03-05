package com.shain.array.pointers.slidingWindow.fixedWindowLength;

public class MinBlack_L2379 {
    public int minimumRecolors(String blocks, int k) {
        int left = 0;
        int right = 0;
        int count = 0;
        int result = blocks.length();

        while (right < blocks.length()) {
            if (blocks.charAt(right) == 'W') {
                count++;
            }

            if (right - left + 1 == k) {
                result = Math.min(count, result);
                count = blocks.charAt(left) == 'W'? count-1: count;
                left++;
            }
            right++;
        }

        return result;
    }
}
