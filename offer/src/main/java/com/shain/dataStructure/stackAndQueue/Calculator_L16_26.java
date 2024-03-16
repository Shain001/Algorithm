package com.shain.dataStructure.stackAndQueue;

import java.util.Deque;
import java.util.LinkedList;

public class Calculator_L16_26 {
    public static void main(String[] args) {
        var test = new Calculator_L16_26();
        System.out.println(test.calculate("0-2147483647"));
    }
    public int calculate(String s) {
        Deque<Long> stack = new LinkedList<>();

        int i = 0;
        while (i < s.length()) {

            // get the calculator
            char c = ' ';
            while (s.charAt(i) == ' ') {
                i++;
            }

            if (!Character.isDigit(s.charAt(i))) {
                c = s.charAt(i);
                i++;
            }

            // get number
            long n = 0;
            while (i < s.length() && (Character.isDigit(s.charAt(i)) || s.charAt(i) == ' ')) {
                if (s.charAt(i) == ' ') {
                    i++;
                    continue;
                }
                n = n * 10 + (s.charAt(i) - '0');
                i++;
            }

            // process number
            switch (c) {
                // the first number
                case ' ':
                    stack.push(n);
                    break;
                case '+':
                    stack.push(n);
                    break;
                case '-':
                    stack.push(-1 * n);
                    break;
                case '*':
                    stack.push(n * stack.pop());
                    break;
                case '/':
                    stack.push(stack.pop() / n);
                    break;
                default:
                    break;
            }
        }

        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }
}
