package com.shain.greedy.maxtomin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MakeCharUnique_L1647 {
    // 创建以数组。 数组下标表示字符出现的频次
    // value表示该频次出现的次数。
    // 题目所求等价于使得该数组中的value均不大于1。
    // 因为只能进行删除字符的操作。即代表， 能够对count数组进行的操作只能是 把 右边的 数字， 往左边挪。
    // 每挪1， 即相当于删除一个字符。
    // 只需从右向左iterate count 数组。 计算答案即可
    public int minDeletions(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0)+1);
        }

        int[] count = new int[s.length()+1];
        for (Integer n : map.values()) {
            count[n]++;
        }

        int ans = 0;
        for (int i = s.length(); i >= 1; i--) {
            if (count[i] > 1) {
                ans += count[i]-1;
                if (i >= 1) {
                    count[i-1] += count[i]-1;
                }
            }
        }

        return ans;
    }


    public int minDeletions_v2(String s) {
        int[] cnt = new int[26];
        for (char ch : s.toCharArray()) {
            cnt[ch - 'a']++;
        }
        Arrays.sort(cnt);

        int ret = 0;
        int prev = cnt[25];
        for (int i = 24; i >= 0 && cnt[i] > 0; i++) {
            // 小于号是为了 两个以上连续数字的情况， e.g. 5 4 4 4 2 2 1
            if (prev <= cnt[i]) {
                prev = Math.max(prev - 1, 0);
                ret += (cnt[i] - prev);
            } else {
                prev = cnt[i];
            }
        }
        return ret;
    }
}
