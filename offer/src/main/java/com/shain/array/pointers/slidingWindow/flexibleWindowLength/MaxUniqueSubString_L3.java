package com.shain.array.pointers.slidingWindow.flexibleWindowLength;

import java.util.HashMap;
import java.util.Map;

public class MaxUniqueSubString_L3 {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        Map<Character, Integer> occurred = new HashMap<>();
        int left = 0;
        int right = 0;
        int ans = 0;

        while (right < s.length()) {
            char currentChar = s.charAt(right);

            // 这里必须要加上 occurred.get(currentChar) >= left 这个条件
            // 否则 left 可能会 "回退到已经不再窗口内的字符"
            // e.g. 输入 abba， 当right遇到第二个b之后， left指向第二个b， 如果不加这个判断， right遇到第二个a以后， 由于
            // 第一个a 还是在map中的， 没有删除， 所以left 会被指向第一个 a， index=0；
            if (occurred.containsKey(currentChar) && occurred.get(currentChar) >= left) {
                left = occurred.get(currentChar) + 1;
            }
            // 更新当前字符的位置
            occurred.put(currentChar, right);
            // 计算当前无重复子串的长度，更新答案
            ans = Math.max(ans, right - left + 1);
            right++;
        }

        return ans;

    }

    public static void main(String[] args) {
        var test = new MaxUniqueSubString_L3();
        System.out.println(test.lengthOfLongestSubstring("abba"));
    }
}
