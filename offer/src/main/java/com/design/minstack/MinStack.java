package com.design.minstack;

import java.util.Deque;
import java.util.LinkedList;

class MinStack {
    private final Deque<Long> stack;
    private long min;

    public MinStack() {
        this.stack = new LinkedList<>();
        this.min = 0;
    }

    // stack 中存放的是 各个val 和 这个val被push进栈时的min 的差值。
    // 如果 被push的值小于 min， 则应更新min。
    // 但是注意， 为了能够还原 前一个min， 应该 后更新min， 即
    // e.g. min = 4, val = 1。 来了一个更小的val。 所以应该更新min = 1，
    // 但是 如果1 被pop出去了， 我们要还原 min 为4。
    // 所以， val = 1 来的时候， 应该先 push -3 进栈， 然后再更新 min。
    // 这样， 当 1 被pop出去的时候， stack返回的是 -3， 而一旦见到 负值， 就知道 当前pop出来的数字就是 当前的最小值。
    // 则此时直接返回 min 值， 同时更新min 为上一个最小值， 上一个min = min - (-3)
    // （如果有几个相同的最小值怎么办？ 比如 多个 1 被push到占中 -> 无所谓， 因为如果有更当前min相等的
    // val 被push进栈， stack中存的是0， 不小于0）
    public void push(int val) {
        if (stack.isEmpty()) {
            min = val;
            stack.push(0L);
            return;
        }

        stack.push(val - min);

        if (min > val) {
            min = val;
        }
    }

    public void pop() {
        if (stack.isEmpty()) {
            return;
        }

        long peek = stack.pop();

        if (peek < 0) {
            min -= peek;
        }
    }

    public int top() {
        if (stack.isEmpty()) {
            return -1;
        }

        long peek = stack.peek();
        if (peek < 0) {
            return (int) min;
        }

        return (int) ((int) peek + min);
    }

    public int getMin() {
        return (int) min;
    }
}