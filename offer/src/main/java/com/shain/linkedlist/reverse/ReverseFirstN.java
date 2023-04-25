package com.shain.linkedlist.reverse;

import com.shain.common.linkList.LinkedListUtils;
import com.shain.common.linkList.ListNode;

/**
 * Reverse first N nodes of the list
 */
public class ReverseFirstN {
    private static ListNode newTail=null;
    public static void main(String[] args) {
        ListNode head = LinkedListUtils.getTestCase(5);
        System.out.println(reverseN(head, 5));
    }

    public static ListNode reverseN(ListNode head, int n) {
        return doReverse(head, n);
    }

    public static ListNode doReverse(ListNode cur, int n) {
        if (n == 1) {
            newTail = cur.next;
            return cur;
        }

        // 以整体来看， doReverse的作用为：
        // 1. 反转n个节点
        // 2. 返回新的head
        // 因此， 要反转n个节点， 即相当于 反转了 后n-1个节点以后， 将新的head的next指向第1个节点， 也即line 32.
        ListNode newHead = doReverse(cur.next,n - 1);
        cur.next.next = cur;
        cur.next = newTail;
        return newHead;
    }
}
