package com.shain.binaryTree.backtrack;

import java.util.*;

public class WordBreak_L139 {
    private Set<String> words;
    private Map<Integer, Boolean> cache;

    public static void main(String[] args) {
        var test = new WordBreak_L139();
        var list = new ArrayList<String>();
        list.add("aaaa");
        list.add("aaa");
        System.out.println(test.dp("aaaaaaa", list));

    }
    public boolean dp(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;

        for (int start = 0; start < s.length(); start++) {
            for (String word: wordDict) {
                int len = word.length();
                if (start+len < s.length()+1 && word.equals(s.substring(start, start+len))) {
                    dp[start + len] |= dp[start];
                }
            }
        }
        return dp[dp.length-1];
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        words = new HashSet<>(wordDict);
        cache = new HashMap<>();
        return backTrack(s, 0);
    }

    private boolean backTrack(String s, int start) {
        if (start == s.length()) {
            return true;
        }
        if (start > s.length() || cache.containsKey(start)) {
            return false;
        }

        for (String word : words) {
            int len = word.length();
            if (start + len <= s.length() && s.substring(start, start + len).equals(word)) {

                if (backTrack(s, start + len)) {
                    return true;
                }
            }
        }
        cache.put(start, false);
        return false;
    }
}
