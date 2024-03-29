package com.shain.linkedlist.reverse;

import com.shain.common.linkList.LinkedListUtils;
import com.shain.common.linkList.ListNode;

public class ReverseKGroup_L25 {
    private static ListNode startOfNextSubList;

    public static void main(String[] args) {
        ListNode head = LinkedListUtils.getTestCase(5);
        System.out.println(reverseKGroup_review(head, 2));
    }

    // update 2024 Mar
    // 头插法
    public ListNode reverseKGroup_update(ListNode head, int k) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pre = dummy;
        // k 个一组进行头插法法
        // 一组结束以后， pre 指针 指向前一组的尾巴， 也即 前一组的cur。
        // 每组进行之前需要检查 剩余节点长度。
        while (checkLength(pre.next, k)) {
            pre = reverseK(pre, pre.next, k);
        }

        return dummy.next;
    }

    private ListNode reverseK(ListNode pre, ListNode head, int k) {
        // 注意这里是 k = 1, 不是 k = 0;
        while (k > 1) {
            ListNode next = head.next;
            head.next = next.next;
            next.next = pre.next;
            pre.next = next;
            k--;
        }

        return head;

    }

    private boolean checkLength(ListNode head, int k) {
        while (k > 0) {
            if (head == null) {
                return false;
            }

            head = head.next;
            k--;
        }
        return true;
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

        ListNode newHead = reverseK(cur.next, k - 1);
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

    public static ListNode reverseKGroup_review(ListNode head, int k) {

        // check if curHead has enough len, if not return head.
        if (head == null || !hasEnoughLength(head, k)) {
            return head;
        }

        // current.next next points to current
        int tempK = k;
        ListNode next = null;
        ListNode originalHead = head;
        ListNode prev = null;
        while (tempK > 0) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
            tempK--;
        }
        // current next points to the reversed next group's head.
        originalHead.next = reverseKGroup(next, k);
        return prev;
    }

    public static boolean hasEnoughLength(ListNode head, int k) {
        int count = 0;

        while (count < k && head != null) {
            head = head.next;
            count++;
        }

        return count == k;
    }
}
