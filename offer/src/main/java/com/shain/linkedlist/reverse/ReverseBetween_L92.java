package com.shain.linkedlist.reverse;

import com.shain.common.linkList.ListNode;

import static com.shain.linkedlist.reverse.ReverseFirstN.reverseN;

public class ReverseBetween_L92 {
    private ListNode newTail;
    public static void main(String[] args) {

    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        return doReverse(head, left, right);
    }

    private ListNode doReverse(ListNode cur, int left, int right) {
        if ( left == 1) {
            reverseN(cur, right);
        }

        // left-1 作用为一直遍历到 起始节点
        // right-1 的作用， 其实相当于 找到 reverseN 中的 N 值。
        // 即， 当left 和 right一直同时-1， 当m-1 = 1时， 此时 right-1的值就是 right-left。
        // 也即 从left节点开始要反转的节点数量。
        // e.g. 节点长度为10， 反转 4 - 7 之间的节点 -> equals to 从第4个节点开始 反转 7-3 = 4 个节点 （从4 减到 1 减了三次，即从head跳到第四个节点是跳三次， not 4 times）
        cur.next = reverseBetween(cur.next, left-1, right-1);
        return cur;
    }
}
