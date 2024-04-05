package com.shain.dataStructure.stackAndQueue;

import java.util.Deque;
import java.util.LinkedList;

public class DecodeString_L394 {
    public String decodeString(String s) {
        Deque<Integer> nStack = new LinkedList<>();
        Deque<String> strStack = new LinkedList<>();

        int n = 0;
        String currentStr = ""; // 用于累积当前处理的字符串
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                n = n * 10 + (c - '0');
            } else if (c == '[') {
                // 当遇到 '[' 时，将当前的数字和字符串状态压入栈，然后重置
                nStack.push(n);
                strStack.push(currentStr);
                n = 0;
                currentStr = "";
            } else if (c == ']') {
                StringBuilder temp = new StringBuilder();
                int repeatTimes = nStack.pop(); // 出栈数字，即重复次数
                // 构建重复的字符串
                for (int j = 0; j < repeatTimes; j++) {
                    temp.append(currentStr);
                }
                // 将出栈的字符串与重复构建的字符串连接
                currentStr = strStack.pop() + temp.toString();
            } else {
                // 累积当前处理的字符串
                currentStr += c;
            }
        }

        // 最后的currentStr包含了完整的解码字符串
        return currentStr;
    }
}
