package com.shain.linkedlist;

import com.shain.common.linkList.LinkedListUtils;
import com.shain.common.linkList.ListNode;

public class PalindromeList_L234 {
    private ListNode left;

    public static void main(String[] args) {
        ListNode head = LinkedListUtils.getPalindrome();
        System.out.println(isPalindrome_v2(head));
    }

    // 反转+比较
    // 先找到链表中点， 将后半段链表反转， 递归or遍历均可
    // 需改变原链表， 若不想改变原链表则需在return前再次改变链表顺序
    public static boolean isPalindrome_v2(ListNode head) {
        // find the middle point
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // fast 为null， 则长度为偶数， 中点为两个， 后半段起点为第二个中点， 也即slow指向的节点。
        // 反之， 中点为一个， 后半段起点为中点的下一个节点， 即slow.next。
        // 画个图举个例子就想起来了。
        // NOTE: 不可用 fast.next 作为判断条件， 因为fast可能为null；
        ListNode startPoint = fast == null ? slow : slow.next;

        // reverse last part of the list
        ListNode newStartPoint = doReverse(startPoint);
        System.out.println("before recover: " + head);

        // NOTE! 不可用 while head != null 为条件， 因为head后面还是接的整段链表。
        while (newStartPoint != null) {
            if (head.val != newStartPoint.val)
                return false;
            head = head.next;
            newStartPoint = newStartPoint.next;
        }

//        This is not working, but I'm too lazy to make it right
//        恢复原链表
//        doReverse(startPoint);
        System.out.println("after recover: " + head);
        return true;
    }

    private static ListNode doReverse(ListNode startPoint) {
        ListNode prev = null;
        ListNode cur = startPoint;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }

    // purely recursive
    // O(2n), high space complexity
    // 利用递归实现栈的效果进行首尾比较
    public boolean isPalindrome_v1(ListNode head) {
        left = head;
        return doCompare(head);
    }

    private boolean doCompare(ListNode cur) {
        if (cur == null) {
            return true;
        }

        Boolean lastPairEqual = doCompare(cur.next);
        Boolean currentPairEqual = left.val == cur.val;
        left = left.next;
        return currentPairEqual && lastPairEqual;
    }


}
