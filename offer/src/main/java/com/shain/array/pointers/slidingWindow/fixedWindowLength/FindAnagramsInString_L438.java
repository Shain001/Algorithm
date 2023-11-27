package com.shain.array.pointers.slidingWindow.fixedWindowLength;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindAnagramsInString_L438 {
    public static void main(String[] args) {
        var test = new FindAnagramsInString_L438();
        String s = "cbaebabacd";
        String p = "abc";
    }

    // 利用数组+滑动窗口 + 双指针
    // 这个方法好， 记一下
    // 还有 方法是 只用 滑动窗口 + 两个数组 -> 不太好， 略
    // 自己想了一个方法是 滑动窗口+ Hash 字符串 -> 无需额外空间， 但是 虽然也是 On 级别时间复杂度， 但是会慢一点， 略了， 可看lt提交记录
    // 这个hash方法问题是会有hash冲突。 e.g. 3+5 = 8, 2+6 也等于8， 所以需要在冲突时再遍历一次 窗口中的字符进行检查
    // 这种方法只适用于 这道题这种情况。 即窗口中 的字符串与p长度， 元素都相等。
    public List<Integer> findAnagrams_v2(String s, String p) {
        int m = s.length();
        int n = p.length();
        if (m < n)
            return new ArrayList<>();

        int[] hashTable = new int[26];
        for (char ch : p.toCharArray())
            hashTable[ch - 'a']++;

        List<Integer> ret = new ArrayList<>();
        for (int l = 0, r = 0; r < m; r++) {
            hashTable[s.charAt(r) - 'a']--;

            // 这里的while循环其实是起到了if的作用。
            // 看起来比较难理解， 但是把它当成 省略了一个if (hashtable[ ] < 0) 然后在加while能好理解点。
            // 首先， hashTable[] < 0 代表当前 right 指向的字符， 要么不属于 目标target中， 要么 属于target中，但是数量超了
            // 所以此时 left right 两个指针所确定的窗口中的子串已经肯定不符合条件了。
            // 进而进入这个while循环， 恢复刚才 减的数组值， "直到 left 走到right的位置， 就能出while了"
            // 也即在 出了这个while以后， hashTable中的值， 又变成了 p 串中各个字符的个数。
            while (hashTable[s.charAt(r) - 'a'] < 0) {
                hashTable[s.charAt(l) - 'a']++;
                l++;
            }

            // 那么只要 right 指向的字符一直符合条件， 那么就不会进入上面的 while。
            // 进而 当 窗口长度等于p时， 代表着找到了一个目标子串。
            if (r - l + 1 == n)
                ret.add(l);
        }
        return ret;
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
