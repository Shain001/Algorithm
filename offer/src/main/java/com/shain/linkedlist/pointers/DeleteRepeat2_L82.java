package com.shain.linkedlist.pointers;

import com.shain.common.linkList.ListNode;

/**
 * 很烦的一道题。
 *
 * 重点在于记住，理解，注意， cur永远是 不重复的元素。 所以在 if里面的那个while中， 我们只需要更新cur的next指针， 不要移动cur！
 * 否则会出现对于输入 1,2,2,3,3,4,4  错误的输出了 1,3,4 这种情况。
 */
public class DeleteRepeat2_L82 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode cur = dummy;

        while (cur.next != null && cur.next.next != null) {
            // cur永远是 不重复节点
            // 所以只需要判断 下两个节点是否重复。
            int nextVal = cur.next.val;

            // 如果 下两个节点相等，则排除所有连续的重复节点。
            if (cur.next.next.val == nextVal) {
                // 如果是 1, 3,3,4,4 -> cur现在指向 1， 所有3被删除， 1 的next指向了4， 但是注意， 此时这个if结束了， 但是cur还是指向1的，
                // 因为我们当前不知道4 是否重复。 在下一个外层while循环中， 会判断4 是否重复， 如果重复的话又被跳过，如果不重复则进入else， cur变成下一个不重复元素。
                while (cur.next != null && cur.next.val == nextVal) {
                    // 删除了所有的 3
                    cur.next = cur.next.next;
                    // cur = cur.next -> 不加这一句是关键， 否则 4 会被加入到结果。一直错在这里
                }
            } else {
                cur = cur.next;
            }

        }
        return dummy.next;
    }
}
