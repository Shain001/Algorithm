package com.design.lfu;

public class BLinkNodeLFU {
    protected int val;
    protected int key;
    protected int freq;
    protected BLinkNodeLFU pre;
    protected BLinkNodeLFU next;

    public BLinkNodeLFU() {
    }

    public BLinkNodeLFU(int key, int val) {
        this.val = val;
        this.key = key;
        this.freq = 1;
    }
}