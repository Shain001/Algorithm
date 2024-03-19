package com.design.lru;

public class BLinkNode {
    protected int val;
    protected int key;
    protected BLinkNode pre;
    protected BLinkNode next;

    public BLinkNode() {
    }

    public BLinkNode(int val, int key) {
        this.val = val;
        this.key = key;
    }
}
