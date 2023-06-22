package com.shain.linkedlist.reverse;

import com.shain.common.linkList.LinkedListUtils;
import com.shain.common.linkList.ListNode;

public class ReverseList_L206 {
    public static void main(String[] args) {
        ListNode head = LinkedListUtils.getTestCase(5);
        System.out.println(reverseListLoop(head));
    }

    public static ListNode reverseList(ListNode head) {
        ListNode test = doReverse(head);
        return test;
    }

    public static ListNode doReverse(ListNode curNode) {
        // wrong 3, forgot the curNode == null
        // this is not only a base case that make the stake starting pop out, but only a null pointer check.
        if (curNode == null || curNode.next == null) {
            return curNode;
        }

        ListNode newHead = doReverse(curNode.next);
        curNode.next.next = curNode;
        curNode.next = null;
        // wrong 1
        // originalNext.next = curNode;
        // wrong 2
        // return curNode;
        return newHead;
    }

    public static ListNode reverseListLoop(ListNode head) {
        // 像你媳妇说的， 做链表题的时候， 别重新排序， 就改箭头。
        // 对于每一个节点而言， 只需要进行一个操作， 即将当前 节点 的next指针指向原本的前一个节点prev。
        ListNode prev = null;

        while (head != null) {
            // 一定要记住， 这里的prev， next， 都是指的原本的 prev 和next。
            ListNode next = head.next;
            head.next = prev;
            // 所以此处， 对head操作了以后， head就变成了 下一个节点的 原本的 prev节点
            prev = head;
            head = next;
        }

        // 此处之所以返回prev， 出循环以后， head指向了空节点， 原本反转之前的链表的空节点的前一个节点， 就是现在的新头节点。
        // 而这个 原本反转之前的链表的空节点的前一个节点  就是用prev表示的
        return prev;
    }
}
