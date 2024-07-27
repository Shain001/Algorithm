package com.shain.dataStructure.stackAndQueue;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class RemovePa_L1249 {
    public String minRemoveToMakeValid(String s) {
        Deque<Integer> stack = new LinkedList<>();
        List<Integer> toRemove = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.offerFirst(i);
            }

            if (s.charAt(i) == ')') {
                if (!stack.isEmpty()) {
                    stack.pollFirst();
                } else {
                    toRemove.add(i);
                }
            }
        }

        while (!stack.isEmpty()) {
            toRemove.add(stack.pollFirst());
        }

        for (int i = 0; i < s.length(); i++) {
            if (toRemove.contains(i)) {
                continue;
            }

            sb.append(s.charAt(i));
        }

        return sb.toString();
    }
}
