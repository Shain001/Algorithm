package com.design.lfu;

import java.util.HashMap;
import java.util.Map;

class LFUCache {
    private final Map<Integer, BLinkNodeLFU> map;
    private final Map<Integer, BLinkNodeLFU> freq;
    private final int CAPACITY;
    private int minFreq;

    public LFUCache(int capacity) {
        CAPACITY = capacity;
        this.map = new HashMap<>();
        this.freq = new HashMap<>();
        minFreq = -1;
    }

    public int get(int key) {
        // 1. get the key from map
        if (!map.containsKey(key)) {
            return -1;
        }

        // 2. increase the freq
        // 2.1 check if the current freq queue is empty, if so remove the freq from map
        // 2.2 check if the minFreq = curFreq, if so increase the cur freq
        var node = map.get(key);
        increaseFreq(node);
        // 3. return the value
        return node.val;
    }

    public void put(int key, int value) {
        // 1. check if exist
        var node = map.get(key);

        // 2. if exist
        // 2.1 update val, increase the freq
        // 2.2 check if the current freq queue is empty, if so remove the freq from map
        // 2.3 check if the minFreq = curFreq, if so increase the cur freq
        if (node != null) {
            node.val = value;
            increaseFreq(node);
            return;
        }
        // 3. if not exist
        checkCapacity();
        // 3.1 create kv in map, make the freq as 1
        // 3.2 update minFreq as 1
        var n = new BLinkNodeLFU(key, value);
        addToHead(n, 1);
        map.put(key, n);
        minFreq = 1;

        // 4. check capacity, remove if reach limit

    }

    private void checkCapacity() {
        // 1. get lowest freq list
        // 2. remove from the last
        // since we check the capacity every time put a new node, so there will be capacity+1 nodes total at most
        // therefore teh lowest freq list will always have one node.
        if (map.size() == CAPACITY) {
            var head = freq.get(minFreq);
            var key = head.pre.key;
            map.remove(key);
            removeFromLink(head.pre, minFreq);
        }
    }

    private void increaseFreq(BLinkNodeLFU node) {
        // remove the node from current link
        int isEmpty = removeFromLink(node, node.freq);
        if (isEmpty == -1 && minFreq == node.freq) {
            minFreq++;
        }

        // put the node to the head of the new link
        addToHead(node, node.freq + 1);
        node.freq += 1;
    }

    private void addToHead(BLinkNodeLFU node, int freq) {
        var head = getOrCreateFreqHead(freq);
        node.pre = head;
        node.next = head.next;

        head.next.pre = node;
        head.next = node;
    }

    // return -1 if the freq list is empty
    // 1 if still has elements
    private int removeFromLink(BLinkNodeLFU node, int f) {
        System.out.println(node.key + "," + node.val + "," + node.freq);
        node.pre.next = node.next;
        node.next.pre = node.pre;

        var head = getOrCreateFreqHead(f);

        if (head.next == head) {
            freq.remove(f);
            return -1;
        }

        return 1;
    }

    private BLinkNodeLFU getOrCreateFreqHead(int f) {
        var head = freq.get(f);
        if (head == null) {
            head = createHead();
            // System.out.println(f);
            freq.put(f, head);
        }

        return head;
    }

    private BLinkNodeLFU createHead() {
        var head = new BLinkNodeLFU();
        head.next = head;
        head.pre = head;
        return head;
    }
}