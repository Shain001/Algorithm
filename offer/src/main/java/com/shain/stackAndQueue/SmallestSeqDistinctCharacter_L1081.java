package com.shain.stackAndQueue;

import java.util.Stack;

/**
 * Greedy
 */
public class SmallestSeqDistinctCharacter_L1081 {
    public static void main(String[] args) {
        char test = 'a';
        System.out.println((int) test);
        System.out.println(smallestSubsequence("cbacdcbc"));
    }

    public static String smallestSubsequence(String s) {
        int ascDiff = 97;
        boolean[] inStack = new boolean[26];
        int[] count = new int[26];
        char[] toChar = s.toCharArray();
        Stack<Character> stack = new Stack<>();

        for (Character c : toChar) {
            int index = c - ascDiff;
            count[index] += 1;
        }

        for (Character c : toChar) {
            int index = c - ascDiff;
            // NOTE: should minus here, before the continue statement
            count[index]--;

            if (inStack[index])
                continue;

            // should be > 0, since what we need to check is whether there is more peeked character remained
            while (!stack.isEmpty() && count[stack.peek() - ascDiff] > 0 && stack.peek() > c) {
                inStack[stack.pop() - ascDiff] = false;
            }

            stack.push(c);

            inStack[index] = true;
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
