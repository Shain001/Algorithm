package com.shain.linkedlist.pointers;

import com.shain.common.linkList.ListNode;

public class RemoveTheLastKthNode {
    public static void main(String[] args) {

    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head;
        ListNode fast = head;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }


        return null;
    }
}
