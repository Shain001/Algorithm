package com.shain.array.pointers.slidingWindow.fixedWindowLength;

import java.util.HashSet;
import java.util.Set;

public class MaxYuanYin_L1456 {
    public int maxVowels(String s, int k) {
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');

        int left = 0;
        int right = 0;
        int count = 0;
        int result = 0;
        while (right < s.length()) {
            if (set.contains(s.charAt(right))) {
                count++;
            }

            if (right - left + 1 > k) {
                if (set.contains(s.charAt(left))) {
                    count--;
                }
                left++;
            }

            result = Math.max(count, result);

            // 注意 right++ 的位置。 不能写在 移动left之前， 自己debug一边就知道了
            right++;
        }

        return result;
    }
}
