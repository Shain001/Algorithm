package com.shain.linkedlist;

import com.shain.common.linkList.LinkedListUtils;
import com.shain.common.linkList.ListNode;


/**
 * You are given the head of a singly linked-list. The list can be represented as:
 * <p>
 * L0 → L1 → … → Ln - 1 → Ln
 * Reorder the list to be on the following form:
 * <p>
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * <p>
 * 题目即是将链表从中点分成两半， 然后将右侧链表区域倒序， 再将两个链表合并
 */
public class ReorderList_L143 {
    public static void main(String[] args) {
        ReorderList_L143 test = new ReorderList_L143();
        ListNode head = LinkedListUtils.getTestCase(5);

        test.reorderList(head);
        System.out.println(head);
    }

    public void reorderList(ListNode head) {
        // 1. find the middle of the linkedList
        ListNode middle = getHeadOfLastHalf(head);

        // 注意此处必须将 middle.next 断开， 否则合并的时候会出现环
        ListNode headOfLastHalf = middle.next;
        middle.next = null;

        // 2. reverse the last half and get new head
        ListNode newHeadOfLastHalf = reverse(headOfLastHalf);

        // 3. merge the two linkedList
        doMerge(head, newHeadOfLastHalf);

    }

    private void doMerge(ListNode leftHead, ListNode rightHead) {
        while (leftHead != null && rightHead != null) {
            ListNode leftNext = leftHead.next;
            ListNode rightNext = rightHead.next;

            leftHead.next = rightHead;
            rightHead.next = leftNext;

            leftHead = leftNext;
            rightHead = rightNext;
        }
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverse(head.next);

        head.next.next = head;
        head.next = null;

        return newHead;
    }

    private ListNode getHeadOfLastHalf(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
