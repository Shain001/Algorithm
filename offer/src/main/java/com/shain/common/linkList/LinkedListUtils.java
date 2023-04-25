package com.shain.common.linkList;

public class LinkedListUtils {
    public static ListNode getTestCase(int length) {
        int count = 1;
        ListNode head = new ListNode(count);
        ListNode toReturn = head;
        while (count < length) {
            head.next = new ListNode(count + 1);
            head = head.next;
            count ++;
        }
        return toReturn;
    }

    public static ListNode getPalindrome() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);
        return head;
    }
}
