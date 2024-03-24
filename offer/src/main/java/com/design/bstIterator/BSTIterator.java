package com.design.bstIterator;

import com.shain.common.tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

class BSTIterator {
    TreeNode cur;
    Deque<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        this.stack = new LinkedList<>();
        this.cur = root;
    }

    public int next() {
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }

        cur = stack.pop();
        var next = cur.val;
        cur = cur.right;

        return next;
    }
    
    public boolean hasNext() {
        return cur != null || !stack.isEmpty();
    }
}