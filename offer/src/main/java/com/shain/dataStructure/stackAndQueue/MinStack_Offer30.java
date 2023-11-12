package com.shain.dataStructure.stackAndQueue;

import java.util.Stack;

class MinStack_Offer30 {

    private Stack<Integer> stack;
    private Stack<Integer> min;
    /** initialize your data structure here. */
    public MinStack_Offer30() {
        stack = new Stack<>();
        min = new Stack<>();
    }

    public void push(int x) {
        if (min.size() == 0 || min.peek() >= x) {
            min.push(x);
        }
        stack.push(x);
    }

    public void pop() {
        // 不要省略 topVal 这一部， 即不要直接写成 if(stack.pop == peek())， 会出错， 不知道为啥
        int topVal = stack.pop();
        if (topVal == min.peek()) {
            min.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min.peek();
    }
}
