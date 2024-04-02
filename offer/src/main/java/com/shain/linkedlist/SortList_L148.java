package com.shain.linkedlist;

import com.shain.common.linkList.ListNode;

import java.util.List;

public class SortList_L148 {
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;

        ListNode tail = head;


        while (tail.next != null) {
            var pre = dummy;
            var p = dummy.next;
            var cur = tail.next;

            if (cur.val >= tail.val) {
                tail = cur;
                continue;
            }

            while (p != tail && p.val <= cur.val) {
                pre = p;
                p = p.next;
            }

            pre.next = cur;
            tail.next = cur.next;
            cur.next = p;
        }

        return dummy.next;
    }

    public ListNode sortList_v2(ListNode head) {
        return mergeSort(head, null);
    }

    private ListNode mergeSort(ListNode head, ListNode tail) {
        if (head == tail) {
            return head;
        }

        // split the list to two
        var slow = head;
        var fast = head;

        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }

        var temp = slow.next;
        slow.next = null;
        var left = mergeSort(head, slow);
        var right = mergeSort(temp, tail);
        var merged = merge(left, right);

        return merged;
    }

    private ListNode merge(ListNode leftHead, ListNode rightHead) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        // Merge until one list is empty
        while (leftHead != null && rightHead != null) {
            if (leftHead.val < rightHead.val) {
                tail.next = leftHead;
                leftHead = leftHead.next;
            } else {
                tail.next = rightHead;
                rightHead = rightHead.next;
            }
            tail = tail.next;
        }

        // Attach the remaining elements of either list
        if (leftHead != null) {
            tail.next = leftHead;
        } else if (rightHead != null) {
            tail.next = rightHead;
        }

        return dummy.next;
    }


}
