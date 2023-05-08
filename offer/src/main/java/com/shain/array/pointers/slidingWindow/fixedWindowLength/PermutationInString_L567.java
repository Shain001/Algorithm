package com.shain.array.pointers.slidingWindow.fixedWindowLength;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PermutationInString_L567 {
    public static void main(String[] args) {
        String tar = "trinitrophenylmethylnitramine";
        String s = "dinitrophenylhydrazinetrinitrophenylmethylnitramine";
        System.out.println(checkInclusion(s, tar));
    }

    public static boolean checkInclusion(String s, String target) {
        int left = 0;
        int right = 0;
        int countSatisfied = 0;

        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> need = target.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toMap(c -> c, c -> 1, Integer::sum));

        while (right < s.length()) {
            // 1. add to window
            char curChar = s.charAt(right);
            right++;
            window.put(curChar, window.getOrDefault(curChar, 0) + 1);

            // 2. update countSatisfied
            if (need.containsKey(curChar)) {
                need.put(curChar, need.get(curChar) - 1);
                countSatisfied = need.get(curChar) == 0 ? countSatisfied + 1 : countSatisfied;
            }

            // 3. shrink window and check
            if (right - left == target.length()) {
                if (countSatisfied == need.size())
                    return true;
                // 3.1 do remove
                char removed = s.charAt(left);
                window.put(removed, window.get(removed) - 1);
                left++;
                // 3.2
                if (need.containsKey(removed)) {
                    need.put(removed, need.get(removed) + 1);
                    // if not in anymore, start adding value to window.
                    // NOTE: This condition HAS to be checking if the value is equal to 1,
                    // Since only when needs[c] change from 0 to 1, then the countSatisfied should increase 1
                    // Specifically, here are the senarios
                    // needs[c] 由负数变负数， 代表窗口内还有足够数量的c
                    // needs[c] 由-1 变0， 还是由足够数量的c
                    // needs[c] 由0 变1， 窗口内消失了足够数量的c， 代表满足条件的char应减少一个， 也即 countSatisfy 应-1
                    // needs[c] 由1 变2， 窗口内"没有消失更多的元素， 缺的还是c， 只是c需要的数量从1个变成了2个"， countStisfy 值不变
                    // If you forgot this part, go to Leetcode 76, and change line 66 from ==1 to ==0, you would find it can't pass
                    countSatisfied = need.get(removed) == 1 ? countSatisfied - 1 : countSatisfied;
                }
            }

        }
        return false;
    }
}
