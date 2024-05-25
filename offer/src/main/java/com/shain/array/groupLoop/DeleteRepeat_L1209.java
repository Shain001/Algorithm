package com.shain.array.groupLoop;

import java.util.Deque;
import java.util.LinkedList;

public class DeleteRepeat_L1209 {
    public static void main(String[] args) {
        var test = new DeleteRepeat_L1209();
        System.out.println(test.removeDuplicates_v2("deeedbbcccbdaa", 3));
    }

    public String removeDuplicates_v2(String s, int k) {
        Deque<Integer> stack = new LinkedList<>();
        StringBuilder sb = new StringBuilder(s);

        for (int i = 0; i < sb.length(); i++) {
            if (i == 0 || sb.charAt(i) != sb.charAt(i-1)) {
                stack.offerFirst(1);
                continue;
            }

            var count = stack.pollFirst() + 1;

            if (count == k) {
                sb.delete(i - k + 1, i+1);
                // 这里得是 i -k, 因为 for循环还会加1
                // 这里是更新 i 的位置， 因为sb的长度已经变了， i也要跟着变。
                i = i-k;
            } else {
                stack.offerFirst(count);
            }
        }

        return sb.toString();
    }

    public String removeDuplicates(String s, int k) {
        if (s.length() < k || !check(s, k)) {
            return s;
        }

        char[] converted = s.toCharArray();

        int i = 0;
        while (i < s.length()-1) {
            while (i < s.length()-1 && converted[i] != converted[i+1]) {
                i++;
                continue;
            }

            int j = i;
            int count = 1;
            boolean flag = true;
            while (j < s.length()-1 && count < k) {
                if (converted[j] != converted[j+1]) {
                    flag = false;
                    break;
                }
                count++;
                j++;
            }

            if (count < k) {
                flag = false;
            }

            if (flag) {
                while (i <= j) {
                    converted[i++] = ' ';
                }
            } else {
                i = j;
            }
        }

        return removeDuplicates(new String(converted).replaceAll(" ", ""), k);

    }

    private boolean check(String removed, int k) {
        int count = 1;
        int temp = 1;
        for (int i = 1; i < removed.length(); i++) {
            if (removed.charAt(i) == removed.charAt(i-1)) {
                temp++;
                count = Math.max(temp, count);
            } else {
                temp = 1;
            }
        }
        return count >= k;
    }
}
