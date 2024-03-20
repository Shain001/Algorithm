package com.design.lfu;

public class Test {
    public static void main(String[] args) {
        // 初始化 LFUCache，容量为 2
        LFUCache lfuCache = new LFUCache(2);

        // 插入键值对 (1,1)
        lfuCache.put(1, 1);

        // 插入键值对 (2,2)
        lfuCache.put(2, 2);

        // 获取键为 1 的值，预期输出 1
        System.out.println("Get 1: " + lfuCache.get(1));

        // 插入键值对 (3,3)，会导致键为 2 的键值对被淘汰
        lfuCache.put(3, 3);

        // 尝试获取键为 2 的值，预期输出 -1（因为键为 2 的已被淘汰）
        System.out.println("Get 2: " + lfuCache.get(2));

        // 获取键为 3 的值，预期输出 3
        System.out.println("Get 3: " + lfuCache.get(3));

        // 插入键值对 (4,4)，会导致键为 1 的键值对被淘汰
        lfuCache.put(4, 4);

        // 尝试获取键为 1 的值，预期输出 -1（因为键为 1 的已被淘汰）
        System.out.println("Get 1: " + lfuCache.get(1));

        // 获取键为 3 的值，预期输出 3
        System.out.println("Get 3: " + lfuCache.get(3));

        // 获取键为 4 的值，预期输出 4
        System.out.println("Get 4: " + lfuCache.get(4));
    }

}
