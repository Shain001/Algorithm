package com.shain.array.pointers.slidingWindow.fixedWindowLength;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindAnagramsInString_L438 {
    public static void main(String[] args) {

    }

    public List<Integer> findAnagrams(String s, String p) {
        int left = 0;
        int right = 0;
        int countSatisfied = 0;
        List<Integer> result = new ArrayList<>();

        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> need = p.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toMap(c -> c, c -> 1, Integer::sum));

        while (right < s.length()) {
            char curChar = s.charAt(right);
            window.put(curChar, window.getOrDefault(curChar, 0) + 1);
            right++;

            if (need.containsKey(curChar)) {
                need.put(curChar, need.get(curChar) - 1);
                countSatisfied = need.get(curChar) == 0 ? countSatisfied + 1 : countSatisfied;
            }

            if (right - left == p.length()) {
                if (countSatisfied == need.size())
                    result.add(left);

                char removed = s.charAt(left);
                window.put(removed, window.get(removed) - 1);
                left++;

                if (need.containsKey(removed)) {
                    need.put(removed, need.get(removed) + 1);
                    countSatisfied = need.get(removed) == 1 ? countSatisfied - 1 : countSatisfied;
                }
            }
        }

        return result;
    }
}
