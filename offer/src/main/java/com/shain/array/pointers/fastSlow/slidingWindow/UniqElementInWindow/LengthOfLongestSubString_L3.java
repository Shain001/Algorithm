package com.shain.array.pointers.fastSlow.slidingWindow.UniqElementInWindow;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubString_L3 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    public static int lengthOfLongestSubstring(String s) {
        int left = 0;
        int right = 0;
        // NOTE: 别初始化result为-1， 0即可， 因为s为空时result正好应该是0
        int result = 0;
        int tempLength = 0;
        Map<Character, Integer> window = new HashMap<>();

        while (right < s.length()) {
            char curChar = s.charAt(right);
            window.put(curChar, window.getOrDefault(curChar,0)+1);
            // Note: tempLength++应该在此处, 而非在28
            // From logical level， tempLength 记录的应该是窗口的大小
            // 如果当前窗口中没有重复， 则继续加入元素， 并且增加窗口长度， 也即tempLength.
            // 如果窗口中出现了重复， 则在嵌套的while中， 会pop out 元素， 直到没有重复。 在此过程中则会减少tempLength.
            // 如果tempLength在if中++， 则会导致 while中tempLength--以后， tempLength的值会比正确值少1， 因为使得window[curChar]=2
            // 的那个curChar 没有被加到 tempLength中。
            // If you can't understand later, try test case "pwwkew" and do a debug.
            tempLength++;
            right++;

            if (window.get(curChar) == 1) {
                // wrong version:
                // tempLength++
                result = Math.max(result, tempLength);
                continue;
            }

            while (window.get(curChar) > 1) {
                char removed = s.charAt(left);
                window.put(removed, window.get(removed)-1);
                left++;
                tempLength--;
            }
        }
        return result;
    }

}
