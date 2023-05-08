package com.shain.linkedlist.pointers;

import com.shain.common.linkList.ListNode;

public class EntryOfCycleList_L22 {
    public static void main(String[] args) {

    }

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // 若链表无环， 则fast会走到链表结尾， 而此时slow还在链表中间。
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                slow = head;
                break;
            }
        }

        // 如果链表中没有环， 该while condition 中的前两个条件会跳过该while， 直接return。
        while (fast != null && fast.next != null && slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        // 如果fast为null 或者fast.next为null， 代表链表无环
        return fast == null || fast.next == null ? null : slow;
    }
}
