package com.shain.array.pointers.fastSlow.slidingWindow.flexibleWindowLength;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MunWindowSubstring_L76 {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t));
    }

    public static String minWindow(String s, String t) {
        int left = 0;
        int right = 0;
        int resultLen = Integer.MAX_VALUE;
        int resultStart = 0;
        // number of targeted chars that have been found.
        int countSatisfied = 0;
        Map<Character, Integer> curWindow = new HashMap<>();
        Map<Character, Integer> needs = t.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toMap(c-> c, c -> 1, Integer::sum));

        while (right < s.length()) {
            // 1. put the current char to the window
            char curChar = s.charAt(right);
            // NOTE: 在此处就使right+1, 目的是使其起到类似 "使window变成左闭右开区间"的效果。
            // This is why in line 47, right-left 就是窗口中元素的数量， 而不用使用 right-left+1 计算元素中数量。
            right ++;
            curWindow.put(curChar, curWindow.getOrDefault(curChar, 0) + 1);

            // 2. if current char is a targeted value, put it in the needs
            if (needs.containsKey(curChar)) {
                // 无需担心是负数的情况， 如果needs[c]值为负， 代表 target中只有5个c， 但是window中有7个
                needs.put(curChar, needs.get(curChar)-1);

                // 3. if needs.get(key)=0, means we have enough number of current char,
                // 4. Note that only 0 should be counted as satisified situation, if it's -1, then still not change value of the count.
                countSatisfied = needs.get(curChar) == 0? countSatisfied+1: countSatisfied;
            }

            // 5. check if we need to shrink the window
            while (countSatisfied == needs.size()) {
                // update the result
                if (right-left < resultLen) {
                    resultLen = right-left;
                    resultStart = left;
                }
                // pop out value from window
                char removed = s.charAt(left);
                left += 1;

                curWindow.put(removed, curWindow.get(removed)-1);
                // if the popped value is in the targeted ones, check if we still have that in the window
                if (needs.containsKey(removed)){
                    needs.put(removed, needs.get(removed)+1);
                    // if not in anymore, start adding value to window.
                    // NOTE: This condition HAS to be checking if the value is equal to 1,
                    // Since only when needs[c] change from 0 to 1, then the countSatisfied should increase 1
                    // Specifically, here are the senarios
                    // needs[c] 由负数变负数， 代表窗口内还有足够数量的c
                    // needs[c] 由-1 变0， 还是由足够数量的c
                    // needs[c] 由0 变1， 窗口内消失了足够数量的c， 代表满足条件的char应减少一个， 也即 countSatisfy 应-1
                    // needs[c] 由1 变2， 窗口内"没有消失更多的元素， 缺的还是c， 只是c需要的数量从1个变成了2个"， countStisfy 值不变
                    // If you forgot this part, go to Leetcode 76, and change line 66 from ==1 to ==0, you would find it can't pass
                    // 换句话说， 其原理跟 line 42 一样
                    countSatisfied = needs.get(removed) == 1? countSatisfied - 1: countSatisfied;
                }
            }

        }
        return resultLen == Integer.MAX_VALUE? "": s.substring(resultStart, resultStart+resultLen);
    }
}
