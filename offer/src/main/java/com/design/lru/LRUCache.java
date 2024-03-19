package com.design.lru;

import java.util.HashMap;
import java.util.Map;

class LRUCache {
    private final Map<Integer, BLinkNode> map;
    private final int capacity;
    private final BLinkNode head;
    private final BLinkNode tail;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.head = new BLinkNode();
        this.tail = new BLinkNode();
        this.capacity = capacity;
        this.head.next = this.tail;
        this.tail.pre = this.head;
    }

    public int get(int key) {
        var target = map.get(key);

        if (target == null) {
            return -1;
        }

        moveToHead(target);

        return target.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            var node = map.get(key);
            node.val = value;
            moveToHead(node);
            return;
        }

        var node = new BLinkNode(value, key);
        map.put(key, node);
        addToHead(node);

        checkCapacity();
    }

    private void checkCapacity() {
        while (map.size() > capacity) {
            var tail = this.tail.pre;
            removeFromList(tail);
            map.remove(tail.key);
        }
    }


    private void moveToHead(BLinkNode target) {
        removeFromList(target);
        addToHead(target);
    }

    private void removeFromList(BLinkNode target) {
        target.next.pre = target.pre;
        target.pre.next = target.next;
    }

    private void addToHead(BLinkNode target) {
        // 移动target
        var oldHead = head.next;
        target.next = oldHead;
        oldHead.pre = target;
        head.next = target;
        target.pre = head;
    }
}