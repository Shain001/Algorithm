package com.shain.linkedlist.reverse;

import com.shain.common.linkList.LinkedListUtils;
import com.shain.common.linkList.ListNode;

public class ReverseKGroup_L25 {
    private static ListNode startOfNextSubList;
    public static void main(String[] args) {
        ListNode head = LinkedListUtils.getTestCase(5);
        System.out.println(reverseKGroup(head, 2));
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        return doReverse(head, k);
    }

    private static ListNode doReverse(ListNode oldHeadOfCurrentSubList, int k) {
        if (oldHeadOfCurrentSubList == null || oldHeadOfCurrentSubList.next == null)
            return oldHeadOfCurrentSubList;

        // 3. 检查剩余节点是否小于k个， 若小于则不反转，直接返回当前头节点。
        if (!checkRemainedLength(oldHeadOfCurrentSubList, k))
            return oldHeadOfCurrentSubList;

        // 1. k 个一组反转， 返回下一组子链表的 新的头节点
        // 2. 当前子链表的原有头节点指向 下一组子链表的新的头节点
        // cur.next = reverseK(cur, k);
        ListNode newHeadOfNextSubList = reverseK(oldHeadOfCurrentSubList, k);
        oldHeadOfCurrentSubList.next = doReverse(startOfNextSubList, k);
        // 4. 截取当前子链表， 将剩余链表的起始节点视作 一个新的链表 的头节点进行递归。
        return newHeadOfNextSubList;
    }

    // 从头节点开始反转k个节点
    private static ListNode reverseK(ListNode cur, int k) {
        if (k == 1) {
            startOfNextSubList = cur.next;
            return cur;
        }

        ListNode newHead = reverseK(cur.next, k-1);
        cur.next.next = cur;
        cur.next = startOfNextSubList;
        return newHead;
    }

    static Boolean checkRemainedLength(ListNode cur, int k) {
        for (int i = 0; i < k; i++) {
            if (cur == null)
                return false;
            cur = cur.next;
        }
        return true;
    }
}
