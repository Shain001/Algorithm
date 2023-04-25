package com.shain.linkedlist.pointers;

import com.shain.common.linkList.ListNode;

public class IntersectionOfList_L160 {
    public static void main(String[] args) {

    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB = headB;

        while (pA != pB) {
            pA = pA == null? headB: pA.next;
            pB = pB ==null? headA: pB.next;
        }

        // 若没有交点， while将在pA = pB = null 时退出循环
        return pA;
    }
}
