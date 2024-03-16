package com.shain.dataStructure.stackAndQueue;

import com.design.snakeGame_L353.Pair;

import java.util.Deque;
import java.util.LinkedList;

public class Calculator1_L224 {


    public static void main(String[] args) {
        var test = new Calculator1_L224();
        System.out.println(test.calculate("3+5 / 2"));
    }

    public int calculate(String s) {
        Deque<Long> stack = new LinkedList<>();
        int ans = 0;
        // initialize as +, so that if the first num is positive, it can be processed properly
        char op = '+';
        int i = 0;

        // 每一次 while， 处理了 一组 op和数字的组合。
        while (i < s.length()) {
            long num = 0;
            // process space
            if (s.charAt(i) == ' ') {
                i++;
                continue;
            }

            // process num
            while (i < s.length() && Character.isDigit(s.charAt(i))) {
                num = num*10 + (s.charAt(i)-'0');
                i++;
            }

            // process ()
            // 括号中的运算看成一个整体， 当成一个数字处理。
            // 这个if中可以更新num， 并且不会导致 原有的num值丢失， 是因为 你把它括号整体当成一个数字， 而一个数字前面一定是一个 符号的， 不可能是另一个数字
            if (i < s.length() && s.charAt(i) == '(') {
                // 除去最外层括号对， 递归解决内层 括号对。
                int count = 1;
                int left = i;
                while (count > 0) {
                    i++;
                    if (s.charAt(i) == '(') {
                        count++;
                        continue;
                    }
                    if (s.charAt(i) == ')') {
                        count--;
                    }
                }
                // 这里出了循环以后 i 一定是指向 右括号的
                String temp = s.substring(left+1, i);
                num = calculate(temp);
            }

            // process operator
            // 走到这， i指向的字符， 只可能是 运算符，或者空字符， 但是注意， 同时也可能是 ) ， 比如 输入为 1 + (2+3)的情况
            // 如果是 ）， 那么我们当他是 正常数字的， 因为上面已经递归的出了括号内整体的结果。
            // 如果是 运算符， 下面更新 op 没有疑问
            // 如果是 ' '， 我们也应该更新op， 因为如果是' ' 就不更新的话， 那么op残留着一个有意义的值， 进而push进去一个0， 比如3+5 / 2 这个用例。
            switch (op) {
                case '+' -> stack.push(num);
                case '-' -> stack.push(-num);
                case '*' -> stack.push(stack.pop()*num);
                case '/' -> stack.push(stack.pop()/num);
            }
            // op 的更新是滞后的， 想象第一个数字举例子
            // 这里要进行i的越界是因为有可能输入是 123， 只有一个数字
            // 这里必须这样写， 不能再if中判断当前cur不为空， 否则可能push一个不必要的0进入stack， 比如 3+5 / 2 这个用例会不通过。
            if (i < s.length()) {
                op = s.charAt(i);
            }

            i++;
        }

        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }
}
