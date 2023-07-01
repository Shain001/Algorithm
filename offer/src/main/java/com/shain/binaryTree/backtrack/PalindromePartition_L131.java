package com.shain.binaryTree.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PalindromePartition_L131 {
    private final List<List<String>> result = new ArrayList<>();
    private boolean[][] dp;
    private String s;

    public List<List<String>> partition(String s) {
        this.s = s;
        dp = new boolean[s.length()][s.length()];
        backTrack(new LinkedList<>(), 0);
        return result;
    }

    // dp optimized
    private void backTrack(LinkedList<String> path, int start) {
        if (start == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int len = 1; start + len <= s.length(); len++) {

            int end = start + len - 1;

            if (start + 1 < end - 1 && !dp[start + 1][end - 1]) {
                continue;
            }

            String cur = s.substring(start, end + 1);

            if (!isPalindrome(start, end)) {
                continue;
            }
            dp[start][end] = true;
            path.add(cur);
            backTrack(path, start + len);
            path.removeLast();
        }
    }

    private boolean isPalindrome(int start, int end) {
        return s.charAt(start) == s.charAt(end);
    }

//    private static void backTrack(LinkedList<String> path, int start) {
//        if (start == s.length()) {
//            result.add(new ArrayList<>(path));
//            return;
//        }
//
//        for (int len = 1; start + len <= s.length(); len++) {
//            String cur = s.substring(start, start+len);
//
//            if (!isPalindrome(cur)) {
//                continue;
//            }
//
//            path.add(cur);
//            backTrack(path, start+len);
//            path.removeLast();
//        }
//    }
//
//    private static boolean isPalindrome(String str) {
//        int left = 0;
//        int right = str.length()-1;
//        while (left < right) {
//            if (str.charAt(left) != str.charAt(right)) {
//                return false;
//            }
//            left++;
//            right--;
//        }
//        return true;
//    }
}
