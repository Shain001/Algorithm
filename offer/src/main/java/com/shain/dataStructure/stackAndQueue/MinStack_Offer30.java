package com.shain.dataStructure.stackAndQueue;

import java.util.Stack;

class MinStack_Offer30 {

    Stack<Integer> minStack;
    Stack<Integer> stack;

    /** initialize your data structure here. */
    public MinStack_Offer30() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    
    public void push(int x) {
        stack.push(x);
        if (!minStack.isEmpty() && x < minStack.peek())
            minStack.push(x);
        else if (!minStack.isEmpty() && x >=minStack.peek())
            minStack.push(minStack.peek());
        else {
            minStack.push(x);
        }
            
    }
    
    public void pop() {
        if (stack.isEmpty())
            return;
        stack.pop();
        minStack.pop();
    }
    
    public int top() {
        if (stack.isEmpty())
            return -1;
        return stack.peek();
    }
    
    public int min() {
        if (stack.isEmpty())
            return -1;
        return minStack.peek();
    }
}
