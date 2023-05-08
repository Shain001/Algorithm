package com.shain.binaryTree.multiTree;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class FalttenNestedList_L341 implements Iterator<Integer> {
    private Stack<NestedInteger> stack;

    public FalttenNestedList_L341(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        if (stack.isEmpty())
            return false;

        if (stack.peek().isInteger())
            return true;

        while (!stack.isEmpty() && !stack.peek().isInteger()) {
            NestedInteger cur = stack.pop();
            List<NestedInteger> list = cur.getList();
            for (int i = list.size() - 1; i >= 0; i--) {
                stack.push(list.get(i));
            }
        }

        return hasNext();
    }

    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        List<NestedInteger> getList();
    }

    // wrong version
    // if input [[]], this would has empty stack exception
//    @Override
//    public boolean hasNext() {
//         if (stack.isEmpty())
//             return false;
//
//         while (!stack.peek().isInteger()) {
//             NestedInteger cur = stack.pop();
//             List<NestedInteger> list = cur.getList();
//             for (int i = list.size()-1; i >= 0; i--) {
//                 stack.push(list.get(i));
//             }
//         }
//
//         return true;
//    }
}
