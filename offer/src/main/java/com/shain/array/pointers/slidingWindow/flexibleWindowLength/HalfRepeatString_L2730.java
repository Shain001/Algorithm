package com.shain.array.pointers.slidingWindow.flexibleWindowLength;

public class HalfRepeatString_L2730 {
    public int longestSemiRepetitiveSubstring(String s) {
        int left = 0;
        int right = 0;
        int count = 0;
        int ans = 0;
        int lastRepeat = 0;
        while (right < s.length()) {
            // 移动right
            char cur = s.charAt(right);

            // right == right -1 -> count+1
            // count > 1 -> 移动left到上一个相邻相等的一对元素中的后一个元素坐标， 直到上一个 相邻相等 被排除。 然后count-1
            // count == 1 => 记录当前right， 因为当前right 就是相邻相等的一对元素中的后一个元素坐标
            if (right > 0 && s.charAt(right - 1) == cur) {
                count++;

                if (count == 1) {
                    lastRepeat = right;
                } else {
                    left = lastRepeat;
                    lastRepeat = right;
                    count--;
                }
            }

            ans = Math.max(ans, right - left + 1);
            right++;
        }

        return ans;
    }
}
