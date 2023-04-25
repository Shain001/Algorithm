package com.shain.linkedlist.pointers;

import com.shain.common.linkList.ListNode;

/**
 * Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * <p>
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 * @date: 15/04/2023
 */
public class PartitionList_L86 {
    public static void main(String[] args) {

    }

    public ListNode partition(ListNode head, int x) {
        // split the list into two according to the given value
        ListNode par1Head = new ListNode();
        ListNode par1Dump = par1Head;
        ListNode par2Head = new ListNode();
        ListNode par2Dump = par2Head;

        while (head != null) {
            if (head.val < x) {
                par1Dump.next = head;
                par1Dump = par1Dump.next;
            } else {
                par2Dump.next = head;
                par2Dump = par2Dump.next;
            }

            // 避免链表环
            // 这段作用就是把当前head的next先指向null， 然后再次进入循环以后再将next指向下一个节点， 但实际是多余的，只需像line45那样
            // null一次即可。 BUT， 分割链表时要注意链表的环这一点is worth to notice.
            // ListNode temp = head.next;
            // head.next = null;
            // head = temp;
            head = head.next;
        }

        // 无需在while中使用temp避免环。
        // 因为只有par2中的最后一个节点的next会指向原链表中该节点的next， 所以只需将par2的最后一个节点的next指向null。
        par2Dump.next = null;

        // concat the two list
        par1Dump.next = par2Head.next;

        return par1Head.next;
    }
}
