package com.shain.linkedlist.pointers;

import com.shain.common.linkList.ListNode;

public class DeleteDuplicates_L83 {
    public static void main(String[] args) {

    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null)
            return null;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null) {
            if (slow.val != fast.val) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }

        // 与 leetcode 26 不同， 此处需要将slow.next 指向null
        // 因为不像数组可以返回不同元素的长度k， fast指针走到最后一个重复节点时， 不会将其置null， 导致结果为 1,2,3,3,3
        slow.next = null;
        return head;
    }
}
