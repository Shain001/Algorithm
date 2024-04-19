package com.shain.linkedlist.pointers;

import com.shain.common.linkList.ListNode;

/**
 * 判断环形链表的证明：
 *
 * 如果链表没有环，快指针将达到链表的末尾，算法终止。
 * 如果链表有环，我们可以将链表视为两部分：链表的非环部分和环形部分。假设非环部分长度为L1，环形部分长度为L2。
 * 当慢指针进入环时，快指针已经在环内移动了L1步。此时，如果快慢指针距离为d，那么快指针每次循环可以将这个距离减少1（因为快指针比慢指针每次多移动一步）。
 * 因此，快慢指针最多在d次循环后相遇，这证明了算法的正确性。
 *
 * 找链表环的入口的证明：
 *
 * 前提
 * 链表起点到环入口的距离为 'a'
 * 环入口到快慢指针首次相遇点的距离为 'b'
 * 相遇点回到环入口的距离为 'c'
 *
 * 因此， 整个环的长度为 L = (b+c)
 *
 * 那么， 当 快慢指针第一次相遇的时候， 即 判断环的入口的那个循环中相遇的时刻：
 * 慢指针走过的距离为： a + b (不准确， 但是无所谓， 就算是 a + n*b, 也能被fast中的n给消除)
 * 快指针走过的距离为： a + b + n * (b+c), n 为快指针走过的圈数。
 *
 * 由于 快指针的路程是slow的两倍， 得出：
 * 2 * (a + b) = a + b + n* (b+c)  ==> a + b = n * (b + c) ==> a+b = n * L ==> a = n*L - b
 *
 * 得到 a = n * L -b 以后， 把 n * L 分成 a = (n-1)*L + L - b
 * 因为 L= b+c， L-b也就等于c。 进而得到 a = (n-1)*L + c
 *
 * 现在回看， a = (n-1)*L + c 这个等式， 是在 slow， fast 相遇的那一刻的距离推导出来的， 此时 slow， fast还在相遇位置。
 *
 * 根据这个等式， 我们知道， 如果让 fast 指针从 链表起点走 a 这么多距离， slow保持同速度， 从第一次相遇点出发， slow要走 n-1 圈 （也就是回到相遇点）， 再走 c 这么多距离。
 * 而根据前提中的定义， 两者必定在 环的入口相遇。
 *
 * 这就是为什么， 这样的操作方式两者必定相遇， 并且相遇点必定是环的入口。
 */
public class EntryOfCycleList_L22 {
    public static void main(String[] args) {

    }

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // 若链表无环， 则fast会走到链表结尾， 而此时slow还在链表中间。
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                slow = head;
                break;
            }
        }

        // 如果链表中没有环， 该while condition 中的前两个条件会跳过该while， 直接return。
        while (fast != null && fast.next != null && slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        // 如果fast为null 或者fast.next为null， 代表链表无环
        return fast == null || fast.next == null ? null : slow;
    }
}
