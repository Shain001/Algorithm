package com.shain.linkedlist.pointers;

import com.shain.common.linkList.ListNode;

/**
 * @date: 15/04/2023
 */
public class MergeTwoSortedList_L21 {
    public static void main(String[] args) {

    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode head = new ListNode();
        ListNode dumpHead = head;
        while (list1 != null && list2 != null) {

            if (list1.val <= list2.val) {
                dumpHead.next = list1;
                list1 = list1.next;
            } else {
                dumpHead.next = list2;
                list2 = list2.next;
            }

            dumpHead = dumpHead.next;
        }

        if (list1 != null || list2 != null)
            dumpHead.next = list1 == null ? list2 : list1;

        return head.next;
    }
}
