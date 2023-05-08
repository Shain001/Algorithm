package com.shain.linkedlist.pointers;

import com.shain.common.linkList.ListNode;

public class TheLastKthNode_Offer22 {
    public static void main(String[] args) {

    }


    public ListNode getKthFromEnd(ListNode head, int n) {
        ListNode dumpHead = new ListNode();
        dumpHead.next = head;

        ListNode previous = dumpHead;
        ListNode slow = head;
        ListNode fast = head;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            previous = previous.next;
            slow = slow.next;
            fast = fast.next;
        }

        previous.next = slow.next;
        return dumpHead.next;
    }

    // 找到第倒数n+1个节点即可
    // 此时 dumyhead视作链表中的一个节点， 防止倒数第k个节点是头节点。
    public ListNode getKthFromEndV2(ListNode head, int n) {
        ListNode dumpHead = new ListNode();
        dumpHead.next = head;

        // 此处slow和fast从dumyhead开始， 因为其已经被视作原链表中的一个节点。 为的是更方便的删除
        ListNode slow = dumpHead;
        ListNode fast = dumpHead;

        for (int i = 0; i < n + 1; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;

        return dumpHead.next;
    }
}
