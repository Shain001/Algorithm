package com.shain.stackAndQueue;

import java.util.LinkedList;
import java.util.Queue;

// todo: complete
public class TwoQueueStack_L225 {
    Queue<Integer> queue;
    Queue<Integer> helper;

    public TwoQueueStack_L225() {
        queue = new LinkedList<>();
        helper = new LinkedList<>();
    }

    public void push(int x) {
        helper.offer(x);

        while(!queue.isEmpty()){
            helper.offer(queue.poll());
        }

        while(!helper.isEmpty()) {
            queue.offer(helper.poll());
        }

    }

    public int pop() {
        if (this.empty())
            return -1;
        return queue.poll();
    }

    public int top() {
        return this.empty()? -1: queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
