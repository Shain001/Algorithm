package com.shain.linkedlist.pointers;

import com.shain.common.linkList.ListNode;

import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class MergeKSortedList_L23 {
    public static void main(String[] args) {

    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode();
        ListNode p = head;

        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));

        Stream.of(lists).filter(Objects::nonNull).forEach(pq::add);

        while (!pq.isEmpty()) {
            ListNode curMin = pq.poll();
            p.next = curMin;
            p = p.next;
            if (curMin.next != null)
                pq.add(curMin.next);
        }

        return head.next;
    }
}
