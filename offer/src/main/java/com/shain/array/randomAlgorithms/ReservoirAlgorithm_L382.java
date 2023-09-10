package com.shain.array.randomAlgorithms;

import com.shain.common.linkList.ListNode;

import java.util.Random;

/**
 * 补充随机性证明笔记/blog
 */
public class ReservoirAlgorithm_L382 {
    private int result;
    private Random random;
    private ListNode head;

    public ReservoirAlgorithm_L382(ListNode head) {
        this.head = head;
        this.random = new Random();
    }

    public int getRandom() {
        ListNode p = this.head;
        int count = 1;

        while (p != null) {
            // radom.nextInt取值左闭右开
            if (random.nextInt(0, count) == 0) {
                this.result = p.val;
            }

            p = p.next;
            count++;
        }

        return result;
    }
}
