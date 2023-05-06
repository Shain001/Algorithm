package com.shain.common.tree;

public class TreeNodeWithPointer {
    public int val;
    public TreeNodeWithPointer left;
    public TreeNodeWithPointer right;
    public TreeNodeWithPointer next;

    public TreeNodeWithPointer() {}

    public TreeNodeWithPointer(int _val) {
        val = _val;
    }

    public TreeNodeWithPointer(int _val, TreeNodeWithPointer _left, TreeNodeWithPointer _right, TreeNodeWithPointer _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
