package com.shain.linkedlist.reverse;

import com.shain.common.linkList.LinkedListUtils;
import com.shain.common.linkList.ListNode;

public class ReverseList_L206 {
    public static void main(String[] args) {
        ListNode head = LinkedListUtils.getTestCase(5);
        System.out.println(reverseListLoop(head));
    }

    public static ListNode reverseList(ListNode head) {
        ListNode test = doReverse(head);
        return test;
    }

    public static ListNode doReverse(ListNode curNode) {
        // wrong 3, forgot the curNode == null
        // this is not only a base case that make the stake starting pop out, but only a null pointer check.
        if (curNode == null || curNode.next == null) {
            return curNode;
        }

        ListNode newHead = doReverse(curNode.next);
        curNode.next.next = curNode;
        curNode.next = null;
        // wrong 1
        // originalNext.next = curNode;
        // wrong 2
        // return curNode;
        return newHead;
    }

    public static ListNode reverseListLoop(ListNode head) {
        ListNode prev = null;

        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        return prev;
    }
}
