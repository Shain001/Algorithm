package com.shain.common.linkList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        List<Integer> results = new ArrayList<>();
        ListNode cur = this;
        while (cur != null) {
            results.add(cur.val);
            cur = cur.next;
        }

        return results.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }
}
