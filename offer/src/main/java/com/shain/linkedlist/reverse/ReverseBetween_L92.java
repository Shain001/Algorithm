package com.shain.linkedlist.reverse;

import com.shain.common.linkList.ListNode;

import static com.shain.linkedlist.reverse.ReverseFirstN.reverseN;

public class ReverseBetween_L92 {
    private ListNode newTail;
    private ListNode tail;

    public static void main(String[] args) {
    }

    // 遍历
    public ListNode reverseBetween_updated2(ListNode head, int left, int right) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode p = head;
        ListNode pre = dummy;

        for (int i = 1; i < left; i++) {
            if (p == null) {
                return head;
            }
            pre = pre.next;
            p = p.next;
        }

        pre.next = reverseN(p, right-left+1);
        return dummy.next;
    }

    private ListNode reverseN(ListNode head, int n) {
        if (head == null) {
            return head;
        }

        ListNode pre = null;
        ListNode p = head;
        while (p != null && n > 0) {
            ListNode temp = p.next;
            p.next = pre;
            pre = p;
            p = temp;
            n--;
        }
        head.next = p;
        return pre;
    }

    // 头插法
    public ListNode reverseBetween_updated1(ListNode head, int left, int right) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        // p 始终指向反转区间前一个节点，即不反转区间的最后一个节点， 保持不动。
        ListNode start = dummy;
        // q 始终指向 反转区间的第一个节点， 也即反转之后的新的尾节点， 保持不动。
        ListNode tail = head;
        int i = 0;
        while (i < left-1) {
            start = start.next;
            tail = tail.next;
            i++;
        }

        while (i < right-1) {
            ListNode next = tail.next;
            tail.next = next.next;
            // 注意， next.next 不是等于tail
            next.next = start.next;
            start.next = next;
            i++;
        }

        return dummy.next;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        return doReverse(head, left, right);
    }

    private ListNode doReverse(ListNode cur, int left, int right) {
        if (left == 1) {
            reverseN(cur, right);
        }

        // left-1 作用为一直遍历到 起始节点
        // right-1 的作用， 其实相当于 找到 reverseN 中的 N 值。
        // 即， 当left 和 right一直同时-1， 当m-1 = 1时， 此时 right-1的值就是 right-left。
        // 也即 从left节点开始要反转的节点数量。
        // e.g. 节点长度为10， 反转 4 - 7 之间的节点 -> equals to 从第4个节点开始 反转 7-3 = 4 个节点 （从4 减到 1 减了三次，即从head跳到第四个节点是跳三次， not 4 times）
        cur.next = reverseBetween(cur.next, left - 1, right - 1);
        return cur;
    }

    public ListNode reverseBetween_review(ListNode head, int left, int right) {
        int count = 0;
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        // 1. 找到left节点的前一个节点
        ListNode h = dummyHead;
        while (count < left - 1) {
            h = h.next;
            count++;
        }

        // 2. 断开left前一个节点与left节点， 此时left节点即相当于另一个链表的头节点。
        // 3. 我们要做的， 即将以 left节点为头节点的链表， 反转前 k 个节点
        // 4. 返回反转后的新的头节点， 将 乐翻天节点的头一个节点 next 指向 反转后返回的头节点。
        ListNode originalLeft = h.next;

        h.next = doReverseK(originalLeft, right - left + 1);


        return dummyHead.next;
    }

    private ListNode doReverseK(ListNode head, int k) {
        if (k == 1) {
            tail = head.next;
            return head;
        }

        ListNode reversedHead = doReverseK(head.next, k - 1);
        head.next.next = head;
        head.next = tail;

        return reversedHead;
    }

}
