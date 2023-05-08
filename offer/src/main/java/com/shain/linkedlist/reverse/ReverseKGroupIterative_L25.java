package com.shain.linkedlist.reverse;

import com.shain.common.linkList.LinkedListUtils;
import com.shain.common.linkList.ListNode;

import static com.shain.linkedlist.reverse.ReverseKGroup_L25.checkRemainedLength;

public class ReverseKGroupIterative_L25 {
    private static ListNode curTail = null;

    public static void main(String[] args) {
        ListNode head = LinkedListUtils.getTestCase(6);
        System.out.println(reverseKGroup(head, 2));
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        // 1. 检查剩余长度
        // 2. 反转
        // 3. 反转后将上一个子链的尾接到当前子链的头
        ListNode dumpHead = new ListNode();
        curTail = dumpHead;

        while (checkRemainedLength(head, k)) {
            head = reverseK(head, k);
        }

        // 将curTail 的next指向剩余的nodes, 因为这些nodes长度不够k， 所以不会进入revesreK
        // 所以需要手动接一下
        curTail.next = head;
        return dumpHead.next;
    }

    private static ListNode reverseK(ListNode head, int k) {
        // 保存当前头节点， which is the new tail of current list
        ListNode temp = head;
        int count = 0;

        // 正常的反转链表
        ListNode prev = null;
        while (count < k) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
            count++;
        }

        // curTail 为上一个list的尾节点， 初始值为DumpHead， 方便返回整个链表的新的头节点。
        curTail.next = prev;
        // 更新上一个list的尾节点。
        curTail = temp;

        // return the head of the next list
        return head;
    }
}
